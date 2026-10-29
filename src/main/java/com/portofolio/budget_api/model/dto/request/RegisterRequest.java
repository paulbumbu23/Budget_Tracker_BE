package com.portofolio.budget_api.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email is mandatory!")
    @Email(message = "Invalid email format!")
    private String email;

    @NotBlank(message = "Password is mandatory!")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;
}
