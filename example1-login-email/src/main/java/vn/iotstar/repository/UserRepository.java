package vn.iotstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.iotstar.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);

    /** JOIN FETCH so the role is loaded while the login query runs (no lazy-loading problems later). */
    @Query("""
            select u from User u
            join fetch u.role
            where lower(u.email) = lower(:email)
            """)
    Optional<User> findByEmailWithRole(@Param("email") String email);
}
