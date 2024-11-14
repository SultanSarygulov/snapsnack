package com.software.snapsnack.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.software.snapsnack.model.Payment;
import com.software.snapsnack.model.exception.PaymentNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    private static final String JSON_FILE_PATH = "src/main/resources/payments.json";
    private List<Payment> payments = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PaymentService() {
        loadPaymentsFromJson();
    }

    private void loadPaymentsFromJson() {
        try {
            File jsonFile = new File(JSON_FILE_PATH);
            if (jsonFile.exists()) {
                payments = objectMapper.readValue(jsonFile, new TypeReference<List<Payment>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePaymentsToJson() {
        try {
            objectMapper.writeValue(new File(JSON_FILE_PATH), payments);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Payment> getAllPayments() {
        return payments;
    }

    public Payment getPaymentById(Long paymentId) {
        return payments.stream()
                .filter(payment -> payment.getPaymentId().equals(paymentId))
                .findFirst()
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
        savePaymentsToJson();
    }

    public void updatePayment(Long paymentId, Payment updatedPayment) {
        Payment existingPayment = getPaymentById(paymentId);
        existingPayment.setAmount(updatedPayment.getAmount());
        existingPayment.setMethod(updatedPayment.getMethod());
        existingPayment.setStatus(updatedPayment.getStatus());
        existingPayment.setPaymentDate(updatedPayment.getPaymentDate());
        savePaymentsToJson();
    }

    public void deletePayment(Long paymentId) {
        Payment payment = getPaymentById(paymentId);
        payments.remove(payment);
        savePaymentsToJson();
    }
}
