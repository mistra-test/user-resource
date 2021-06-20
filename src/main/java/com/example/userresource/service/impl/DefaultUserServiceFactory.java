package com.example.userresource.service.impl;

import com.example.userresource.repository.UserRepository;
import com.example.userresource.service.UserService;
import com.example.userresource.validation.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserServiceFactory {

    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;

    @Bean
    UserService userService() {
        DefaultUserService service = new DefaultUserService();
        service.setPasswordEncoder(passwordEncoder);
        service.setUserRepository(userRepository);
        service.setValidator(new UserValidator());
        return service;
    }
}
