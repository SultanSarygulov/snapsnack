package com.software.snapsnack.model.exception;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(Long id) {
        super("We don’t have such a payment with ID: " + id);
    }
}
