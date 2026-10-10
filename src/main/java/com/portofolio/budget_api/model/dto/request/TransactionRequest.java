package com.portofolio.budget_api.model.dto.request;

import com.portofolio.budget_api.model.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TransactionRequest {
    private TransactionType type;
    private String description;
    private BigDecimal amount;
    private String category;
    private LocalDateTime transactionDate;
}
