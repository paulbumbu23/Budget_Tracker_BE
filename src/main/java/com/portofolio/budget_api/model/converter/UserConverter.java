package com.portofolio.budget_api.model.converter;

import com.portofolio.budget_api.model.dto.request.RegisterRequest;
import com.portofolio.budget_api.model.dto.response.UserResponse;
import com.portofolio.budget_api.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User toEntity(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        return user;
    }

    public UserResponse toResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setCreatedAt(user.getCreatedAt());
        return userResponse;
    }
}
