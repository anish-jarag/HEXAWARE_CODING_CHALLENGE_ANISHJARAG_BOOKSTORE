package com.hexaware.codingchallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.codingchallenge.model.UserModel;
import com.hexaware.codingchallenge.service.UserModelService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserModelService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        UserModel user = userService.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body("User not found!");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/")
    public ResponseEntity<?> addUser(@RequestBody UserModel user) {
        UserModel created = userService.addUser(user);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody UserModel updatedUser) {
        UserModel updated = userService.updateUser(username, updatedUser);
        if (updated != null) {
            return ResponseEntity.ok("User updated successfully.");
        } else {
            return ResponseEntity.status(404).body("User not found!");
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        boolean deleted = userService.deleteUser(username);
        if (deleted) {
            return ResponseEntity.ok("User deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("User not found!");
        }
    }
}
