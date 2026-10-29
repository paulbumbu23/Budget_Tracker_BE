package com.portofolio.budget_api.model.dto.request;

import com.portofolio.budget_api.model.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TransactionRequest {
    @NotNull(message = "Transaction type : INCOME/EXPENSE.")
    private TransactionType type;

    private String description;

    @NotNull(message = "Add a value.")
    @Positive(message = "Amount should be greater than 0.")
    private BigDecimal amount;

    @NotNull(message = "Category is mandatory.")
    private String category;
    
    private LocalDateTime transactionDate;
}
