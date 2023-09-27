package com.example.ECommerce_website.service;


import com.example.ECommerce_website.model.CustomUserDetail;
import com.example.ECommerce_website.model.User;
import com.example.ECommerce_website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.map(CustomUserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found..." + email));

    }
}
