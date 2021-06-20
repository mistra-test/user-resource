package com.example.userresource.validation;

import com.example.userresource.model.User;

public class UserValidator implements Validator<User> {

    @Override
    public void validate(User user) throws InvalidUserException {

        if (user.getFirstName() == null) {
            throw new InvalidUserException("Field firstName cannot be null");
        }

        if (user.getLastName() == null) {
            throw new InvalidUserException("Field lastName cannot be null");
        }

        if (user.getEmail() == null) {
            throw new InvalidUserException("Field email cannot be null");
        }

        if (user.getPassword() == null) {
            throw new InvalidUserException("Field password cannot be null");
        }
    }
}
