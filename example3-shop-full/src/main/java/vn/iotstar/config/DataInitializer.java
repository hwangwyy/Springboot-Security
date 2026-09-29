package vn.iotstar.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;
import vn.iotstar.repository.RoleRepository;
import vn.iotstar.repository.UserRepository;

/** Seeds ROLE_USER, ROLE_ADMIN and one ADMIN account (so you don't need the manual SQL INSERTs). */
@Slf4j
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(RoleRepository roles,
                               UserRepository users,
                               PasswordEncoder encoder,
                               @Value("${app.admin.username:admin}") String adminUsername,
                               @Value("${app.admin.email:admin@iotstar.vn}") String adminEmail,
                               @Value("${app.admin.password:123456}") String adminPassword) {
        return args -> {
            roles.findByName("ROLE_USER").orElseGet(() -> roles.save(Role.builder().name("ROLE_USER").build()));
            Role adminRole = roles.findByName("ROLE_ADMIN")
                    .orElseGet(() -> roles.save(Role.builder().name("ROLE_ADMIN").build()));

            if (!users.existsByUsername(adminUsername) && !users.existsByEmail(adminEmail)) {
                users.save(User.builder()
                        .username(adminUsername)
                        .email(adminEmail)
                        .password(encoder.encode(adminPassword))
                        .fullName("Quản trị viên")
                        .enabled(true)
                        .role(adminRole)
                        .build());
                log.info("Seeded admin account '{}' ({})", adminUsername, adminEmail);
            }
        };
    }
}
