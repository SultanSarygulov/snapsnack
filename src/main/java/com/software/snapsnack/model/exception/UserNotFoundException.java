package com.software.snapsnack.model.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("We don’t have such a lesson with ID: " + id);
    }
}
