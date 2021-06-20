package com.example.userresource.service.impl;

import com.example.userresource.model.User;
import com.example.userresource.repository.UserRepository;
import com.example.userresource.service.UserService;
import com.example.userresource.validation.Validator;

import lombok.AccessLevel;
import lombok.Setter;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Setter(AccessLevel.PACKAGE)
public class DefaultUserService implements UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private Validator<User> validator;

    @Override
    public User save(User user) {
        validator.validate(user);
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String rawPassword) {
        return userRepository
                .findByEmail(email)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPassword()));
    }
}
