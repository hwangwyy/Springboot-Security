package vn.iotstar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.iotstar.security.CustomUserDetails;
import vn.iotstar.service.UserService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    /** Shows the MapStruct entity -> DTO mapping in action. */
    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        model.addAttribute("user", userService.findById(principal.getId()));
        return "profile";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin/index";
    }
}
