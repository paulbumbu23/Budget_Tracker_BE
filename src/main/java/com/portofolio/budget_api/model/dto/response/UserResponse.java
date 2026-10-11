package com.portofolio.budget_api.model.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private LocalDate createdAt;
}
