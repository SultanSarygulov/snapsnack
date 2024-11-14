package com.software.snapsnack.model.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("We don’t have such a order with ID: " + id);
    }
}
