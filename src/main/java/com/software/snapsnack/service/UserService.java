package com.software.snapsnack.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.software.snapsnack.model.User;
import com.software.snapsnack.model.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final String JSON_FILE_PATH = "src/main/resources/users.json";
    private List<User> users = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserService() {
        loadUsersFromJson();
    }

    private void loadUsersFromJson() {
        try {
            File jsonFile = new File(JSON_FILE_PATH);
            if (jsonFile.exists()) {
                users = objectMapper.readValue(jsonFile, new TypeReference<List<User>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUsersToJson() {
        try {
            objectMapper.writeValue(new File(JSON_FILE_PATH), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
        saveUsersToJson();
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        existingUser.setName(updatedUser.getName());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setAddress(updatedUser.getAddress());
        saveUsersToJson();
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        users.remove(user);
        saveUsersToJson();
    }
}
