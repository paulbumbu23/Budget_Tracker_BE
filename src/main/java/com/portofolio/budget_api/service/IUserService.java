package com.portofolio.budget_api.service;

import com.portofolio.budget_api.model.dto.request.RegisterRequest;
import com.portofolio.budget_api.model.dto.response.UserResponse;
import com.portofolio.budget_api.model.entity.User;

public interface IUserService {
    UserResponse registerUser(RegisterRequest registerRequest);
}
