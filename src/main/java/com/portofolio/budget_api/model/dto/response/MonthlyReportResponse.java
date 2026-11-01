package com.portofolio.budget_api.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MonthlyReportResponse {
    private int year;
    private int month;

    private BigDecimal totalIncomeRON;
    private BigDecimal totalExpenseRON;

    private BigDecimal balanceRON;
    private BigDecimal balanceEUR;
    private BigDecimal balanceUSD;
}