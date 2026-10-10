package com.portofolio.budget_api.model.dto.response;

import com.portofolio.budget_api.model.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private Long id;
    private BigDecimal amount;
    private String category;
    private LocalDateTime transactionDate;
    private String description;
    private Long userId;
    private TransactionType transactionType;
}
