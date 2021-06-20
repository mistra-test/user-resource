package com.example.userresource.controller;

import com.example.userresource.model.User;
import com.example.userresource.service.UserService;
import com.example.userresource.validation.InvalidUserException;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Data
class EmailAndPasswordDTO {
    String email;
    String password;
}

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired UserService userService;

    @PostMapping
    public User save(@RequestBody UserDTO user) {
        try {
            return userService.save(UserDTO.toUser(user));
        } catch (InvalidUserException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
        }
    }

    @PostMapping("/authenticate")
    public Optional<User> findByEmailAndPassword(
            @RequestBody EmailAndPasswordDTO emailAndPasswordDTO) {
        return userService.findByEmailAndPassword(
                emailAndPasswordDTO.getEmail(), emailAndPasswordDTO.getPassword());
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        return userService.findById(id);
    }
}
