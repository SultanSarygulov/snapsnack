package com.software.snapsnack.model;

public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private Integer rating;

    public Restaurant() {}

    public Restaurant(Long id, String name, String address, Integer rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
}
