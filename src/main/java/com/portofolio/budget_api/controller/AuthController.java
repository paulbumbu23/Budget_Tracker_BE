package com.portofolio.budget_api.controller;

import com.portofolio.budget_api.model.dto.request.RegisterRequest;
import com.portofolio.budget_api.model.entity.User;
import com.portofolio.budget_api.service.IUserService;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
        try {
            User registeredUser = userService.registerUser(registerRequest);
            return ResponseEntity.ok().body(registeredUser.toString());
        } catch (RuntimeException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}

