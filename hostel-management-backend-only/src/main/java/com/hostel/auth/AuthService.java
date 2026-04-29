package com.hostel.auth;

import com.hostel.auth.dto.LoginRequest;
import com.hostel.auth.dto.RegisterRequest;
import com.hostel.user.User;
import com.hostel.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, Object> register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        User savedUser = userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("id", savedUser.getId());
        response.put("fullName", savedUser.getFullName());
        response.put("email", savedUser.getEmail());
        response.put("role", savedUser.getRole());
        response.put("firstLogin", savedUser.isFirstLogin());
        response.put("allocationStatus", savedUser.getAllocationStatus());
        return response;
    }

    public Map<String, Object> login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("token", "sample-jwt-token");
        response.put("id", user.getId());
        response.put("fullName", user.getFullName());
        response.put("email", user.getEmail());
        response.put("role", user.getRole());
        response.put("firstLogin", user.isFirstLogin());
        response.put("allocationStatus", user.getAllocationStatus());
        response.put("allocatedRoomNumber", user.getAllocatedRoomNumber());
        response.put("allocatedBlock", user.getAllocatedBlock());
        response.put("allocatedFloor", user.getAllocatedFloor());
        return response;
    }
}
