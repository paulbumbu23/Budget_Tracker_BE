package com.portofolio.budget_api.service.impl;

import com.portofolio.budget_api.exception.ResourceAlreadyExistsException;
import com.portofolio.budget_api.exception.ResourceNotFoundException;
import com.portofolio.budget_api.model.converter.UserConverter;
import com.portofolio.budget_api.model.dto.request.LoginRequest;
import com.portofolio.budget_api.model.dto.request.RegisterRequest;
import com.portofolio.budget_api.model.dto.response.AuthResponse;
import com.portofolio.budget_api.model.dto.response.UserResponse;
import com.portofolio.budget_api.model.entity.User;
import com.portofolio.budget_api.repository.TransactionRepository;
import com.portofolio.budget_api.repository.UserRepository;
import com.portofolio.budget_api.security.JwtUtil;
import com.portofolio.budget_api.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponse registerUser(RegisterRequest registerRequest) {
        Optional<User> existingUser = userRepository.findByEmail(registerRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new ResourceAlreadyExistsException("User with email " + registerRequest.getEmail() + " already exists");
        }
        User user = userConverter.toEntity(registerRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // password needs to be crypted before saving to db
        User savedUser = userRepository.save(user);

        return userConverter.toResponse(savedUser);

    }

    @Override
    public AuthResponse loginUser (LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        String token = jwtUtil.generateToken(loginRequest.getEmail());
        return new AuthResponse(token, loginRequest.getEmail());

    }

    @Override
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        transactionRepository.deleteByUserId(user.getId());

        userRepository.delete(user);}

}
