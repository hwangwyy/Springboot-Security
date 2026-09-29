package vn.iotstar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.iotstar.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // @EntityGraph loads the owner in the same query and (unlike JOIN FETCH) is safe with Page<> count queries
    @EntityGraph(attributePaths = "user")
    @Query("""
            select p from Product p
            where lower(p.name) like lower(concat('%', :keyword, '%'))
               or lower(coalesce(p.description, '')) like lower(concat('%', :keyword, '%'))
            """)
    Page<Product> search(@Param("keyword") String keyword, Pageable pageable);

    long countByUserId(Long userId);
}
