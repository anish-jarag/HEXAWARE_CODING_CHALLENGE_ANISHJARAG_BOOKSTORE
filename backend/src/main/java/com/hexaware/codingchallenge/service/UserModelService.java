package com.hexaware.codingchallenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.codingchallenge.model.UserModel;
import com.hexaware.codingchallenge.repository.UserRepository;

@Service
public class UserModelService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserModel getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel addUser(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserModel saved = userRepository.save(user);
        return saved;
    }

    public UserModel updateUser(String username, UserModel updatedUser) {
        Optional<UserModel> existing = userRepository.findByUsername(username);
        if (existing.isPresent()) {
            UserModel user = existing.get();
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            user.setRole(updatedUser.getRole());
            return userRepository.save(user);
        }
        return null;
    }

    public boolean deleteUser(String username) {
        Optional<UserModel> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }
}
