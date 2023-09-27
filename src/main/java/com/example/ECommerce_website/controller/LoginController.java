
package com.example.ECommerce_website.controller;

import com.example.ECommerce_website.repository.RollRepository;
import com.example.ECommerce_website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
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
}
