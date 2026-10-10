package com.portofolio.budget_api.model.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}
