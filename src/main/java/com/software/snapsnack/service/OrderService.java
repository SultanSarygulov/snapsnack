package com.software.snapsnack.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.software.snapsnack.model.Order;
import com.software.snapsnack.model.exception.OrderNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private static final String JSON_FILE_PATH = "src/main/resources/orders.json";
    private List<Order> orders = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OrderService() {
        loadOrdersFromJson();
    }

    private void loadOrdersFromJson() {
        try {
            File jsonFile = new File(JSON_FILE_PATH);
            if (jsonFile.exists()) {
                orders = objectMapper.readValue(jsonFile, new TypeReference<List<Order>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveOrdersToJson() {
        try {
            objectMapper.writeValue(new File(JSON_FILE_PATH), orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order getOrderById(Long orderId) {
        return orders.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public void addOrder(Order order) {
        orders.add(order);
        saveOrdersToJson();
    }

    public void updateOrder(Long orderId, Order updatedOrder) {
        Order existingOrder = getOrderById(orderId);
        existingOrder.setUserId(updatedOrder.getUserId());
        existingOrder.setRestaurantId(updatedOrder.getRestaurantId());
        existingOrder.setOrderList(updatedOrder.getOrderList());
        saveOrdersToJson();
    }

    public void deleteOrder(Long orderId) {
        Order order = getOrderById(orderId);
        orders.remove(order);
        saveOrdersToJson();
    }
}
