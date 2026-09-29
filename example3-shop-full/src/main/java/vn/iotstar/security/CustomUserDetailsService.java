package vn.iotstar.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.iotstar.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /** Login with username OR email. Disabled (not yet OTP-verified) accounts are rejected by Spring Security. */
    @Override
    public UserDetails loadUserByUsername(String login) {
        return userRepository.findByUsernameOrEmail(login, login)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Tài khoản không tồn tại"));
    }
}
