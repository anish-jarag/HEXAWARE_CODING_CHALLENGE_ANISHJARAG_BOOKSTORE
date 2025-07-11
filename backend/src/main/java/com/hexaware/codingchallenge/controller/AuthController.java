package com.hexaware.codingchallenge.controller;

import com.hexaware.codingchallenge.model.UserModel;
import com.hexaware.codingchallenge.service.CustomUserDetailsService;
import com.hexaware.codingchallenge.service.UserModelService;
import com.hexaware.codingchallenge.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserModel loginUser) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getUsername(),
                            loginUser.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        UserModel user = userModelService.getUserByUsername(loginUser.getUsername());
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        // generate token with role
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        // return token and role
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("role", user.getRole());
        response.put("username", user.getUsername());

        return ResponseEntity.ok(response);
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserModel newUser) {
        if (userModelService.getUserByUsername(newUser.getUsername()) != null) {
            return ResponseEntity.status(400).body("Username already exists.");
        }

        newUser.setRole("USER"); 
        UserModel createdUser = userModelService.addUser(newUser);
        return ResponseEntity.status(201).body(createdUser);
    }
}
