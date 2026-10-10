package com.portofolio.budget_api.model.converter;

import com.portofolio.budget_api.model.dto.request.RegisterRequest;
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
}
