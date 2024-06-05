package org.example.decorshop.Controller;

import org.example.decorshop.Model.Users;
import org.example.decorshop.Service.UserService;
import org.example.decorshop.security.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

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
        Users userDetail = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetail", userDetail);
        return "redirect:/";
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam String username, @RequestParam String fist_name,
                                  @RequestParam String last_name, @RequestParam String email,
                                  @RequestParam String password, @RequestParam String repeat_password, Model model) {
        Users user = userService.findByUserName(username).orElse(null);
        if(user != null) {
            model.addAttribute("errorMessage", "Username already in used!");
            return "redirect:/register";
        }
        if(!password.equals(repeat_password)) {
            model.addAttribute("errorMessage", "Passwords do not match!");
            return "redirect:/register";
        }
        Users newUser = new Users(username, fist_name, last_name, email, password);
        userService.saveUser(newUser);
//        userService.saveUser(new Users(username, fist_name, last_name, email, password));
        return "redirect:/login";
    }
}
