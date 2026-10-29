package com.portofolio.budget_api.controller;

import com.portofolio.budget_api.model.dto.request.LoginRequest;
import com.portofolio.budget_api.model.dto.request.RegisterRequest;
import com.portofolio.budget_api.model.dto.response.AuthResponse;
import com.portofolio.budget_api.model.dto.response.UserResponse;
import com.portofolio.budget_api.model.entity.User;
import com.portofolio.budget_api.service.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.loginUser(request));
    }
}

