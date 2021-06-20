package com.example.userresource.controller;

import com.example.userresource.model.User;

import lombok.Data;

@Data
class UserDTO {
    private Long id;

    private String firstName;
    private String lastName;

    private String email;
    private String password;

    public static User toUser(UserDTO dto) {
        User user = new User();

        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }
}
