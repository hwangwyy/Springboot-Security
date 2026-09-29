package vn.iotstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    String home() {
        return "home";
    }

    /** ADMIN only (see SecurityConfig). */
    @GetMapping("/dashboard")
    String dashboard() {
        return "dashboard";
    }
}
