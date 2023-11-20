package com.example.lab09.service;


import com.example.lab09.model.User;
import com.example.lab09.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public boolean isUserExists(String email) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        return existingUser.isPresent();
    }

    public boolean validateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map(user -> user.getPassword().equals(password)).orElse(false);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Phương thức để lấy user theo ID
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
