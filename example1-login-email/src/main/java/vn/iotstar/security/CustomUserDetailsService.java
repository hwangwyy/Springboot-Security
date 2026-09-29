package vn.iotstar.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.iotstar.entity.User;
import vn.iotstar.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository users;

    /** The "username" here is the e-mail typed in the login form. */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = users.findByEmailWithRole(email)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản."));

        return org.springframework.security.core.userdetails.User.withUsername(u.getEmail())
                .password(u.getPassword())
                .roles(u.getRole().getName())   // "ADMIN" -> ROLE_ADMIN
                .disabled(!u.isEnabled())
                .build();
    }
}
