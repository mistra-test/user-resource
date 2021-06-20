package com.example.userresource.validation;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String error) {
        super(error);
    }
}
