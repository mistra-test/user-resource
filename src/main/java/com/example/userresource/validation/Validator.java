package com.example.userresource.validation;

public interface Validator<E> {
    void validate(E entity);
}
