
package com.example.ECommerce_website.controller;

import com.example.ECommerce_website.configuration.SecurityConfig;
import com.example.ECommerce_website.model.User;
import com.example.ECommerce_website.repository.RollRepository;
import com.example.ECommerce_website.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    SecurityConfig securityConfig;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RollRepository rollRepository;

    @GetMapping("/login")
    public String loginGet()
    {
        return "login";
    }
    @GetMapping("/register")
    public String registerGet()
    {
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException
    {
        String password = user.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = securityConfig.passwordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return "/shop";
    }
}
