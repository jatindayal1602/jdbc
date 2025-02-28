package com.example.jdbc.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jdbc.Model.User;
import com.example.jdbc.exceptions.UserNotFoundException;
import com.example.jdbc.repositories.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Save user to the database
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found!"));
    }
    // public User getUserById(Long id) {
    //     return userRepository.findById(id)
    //             .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found!"));
    // }
     // Update user (Update)
     public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found!"));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());

        return userRepository.save(existingUser);
    }

    // Delete user (Delete)
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found!"));

        userRepository.delete(user);
    }
}
