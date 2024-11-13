package com.software.snapsnack.model;

import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private Long restaurantId;
    private List<MenuItem> orderList;

    public Order() {}

    public Order(Long id, Long userId, Long restaurantId, List<MenuItem> orderList) {
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.orderList = orderList;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getRestaurantId() { return restaurantId; }
    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }

    public List<MenuItem> getOrderList() { return orderList; }
    public void setOrderList(List<MenuItem> rating) { this.orderList = orderList; }
}
