package com.portofolio.budget_api.model.dto.request;

import com.portofolio.budget_api.model.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Currency is mandatory (e.g., RON, EUR, USD)")
    private String currency;

    private LocalDateTime transactionDate;
}
