package com.software.snapsnack.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.software.snapsnack.model.MenuItem;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuItemService {
    private static final String JSON_FILE_PATH = "src/main/resources/menuItems.json";
    private List<MenuItem> menuItems;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MenuItemService() {
        loadMenuItems();
    }

    private void loadMenuItems() {
        try {
            MenuItem[] menuItemArray = objectMapper.readValue(new File(JSON_FILE_PATH), MenuItem[].class);
            this.menuItems = List.of(menuItemArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItems;
    }

    public Optional<MenuItem> getMenuItemById(Long id) {
        return menuItems.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public List<MenuItem> getMenuItemsByRestaurantId(Long restaurantId) {
        return menuItems.stream()
                .filter(item -> item.getRestaurantId().equals(restaurantId))
                .collect(Collectors.toList());
    }
}
