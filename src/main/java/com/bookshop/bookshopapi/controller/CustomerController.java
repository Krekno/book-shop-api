package com.bookshop.bookshopapi.controller;

import com.bookshop.bookshopapi.DTO.LoginRequest;
import com.bookshop.bookshopapi.DTO.LoginResponse;
import com.bookshop.bookshopapi.DTO.RegisterRequest;
import com.bookshop.bookshopapi.DTO.RegisterResponse;
import com.bookshop.bookshopapi.entity.User;
import com.bookshop.bookshopapi.enums.Role;
import com.bookshop.bookshopapi.repository.UserRepository;
import com.bookshop.bookshopapi.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class CustomerController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);

        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}