package com.software.snapsnack.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.software.snapsnack.model.Restaurant;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    private static final String JSON_FILE_PATH = "src/main/resources/restaurants.json";
    private List<Restaurant> restaurants;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RestaurantService() {
        loadRestaurants();
    }

    private void loadRestaurants() {
        try {
            Restaurant[] restaurantArray = objectMapper.readValue(new File(JSON_FILE_PATH), Restaurant[].class);
            this.restaurants = List.of(restaurantArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurants;
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurants.stream().filter(r -> r.getId().equals(id)).findFirst();
    }
}
