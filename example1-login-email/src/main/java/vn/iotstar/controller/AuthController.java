package vn.iotstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    /** POST /login is handled by Spring Security itself. */
    @GetMapping("/login")
    String login() {
        return "auth/login";
    }

    @GetMapping("/access-denied")
    String accessDenied() {
        return "auth/access-denied";
    }
}
