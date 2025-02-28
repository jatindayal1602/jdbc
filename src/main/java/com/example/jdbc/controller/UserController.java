package com.example.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jdbc.Model.User;
import com.example.jdbc.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    @Autowired
    private UserService userService;

    // Insert User (POST)
    @PostMapping("/add")
    @Operation(summary = "Add a user", description = "Add a user to list")
    public User addUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    // Get All Users (GET)
    @GetMapping("/all")
    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve a user by their ID")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Update User (PUT)
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,@Valid @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    // Delete User (DELETE)
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User with ID " + id + " deleted successfully!";
    }
    @GetMapping("/name/{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }
}
