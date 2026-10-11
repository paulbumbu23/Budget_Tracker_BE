package com.portofolio.budget_api.service.impl;

import com.portofolio.budget_api.exception.ResourceAlreadyExistsException;
import com.portofolio.budget_api.model.converter.UserConverter;
import com.portofolio.budget_api.model.dto.request.RegisterRequest;
import com.portofolio.budget_api.model.dto.response.UserResponse;
import com.portofolio.budget_api.model.entity.User;
import com.portofolio.budget_api.repository.UserRepository;
import com.portofolio.budget_api.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public UserResponse registerUser(RegisterRequest registerRequest) {
        Optional<User> existingUser = userRepository.findByEmail(registerRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new ResourceAlreadyExistsException("User with email " + registerRequest.getEmail() + " already exists");
        }
        User user = userConverter.toEntity(registerRequest);
        User savedUser = userRepository.save(user);

        return userConverter.toResponse(savedUser);

    }
}
