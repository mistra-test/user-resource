package com.example.userresource.service;

import com.example.userresource.model.User;

import java.util.Optional;

public interface UserService {

    public User save(User user);

    public void deleteById(Long id);

    public Optional<User> findById(Long id);

    public Optional<User> findByEmailAndPassword(String email, String password);
}
