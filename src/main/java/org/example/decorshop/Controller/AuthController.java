package org.example.decorshop.Controller;

import org.example.decorshop.Model.Users;
import org.example.decorshop.security.CustomUserDetail;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/welcome-success")
    public String processLogin(Model model) {
        Users userDetail=  (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetail", userDetail);
        return "redirect:/";
    }
}
