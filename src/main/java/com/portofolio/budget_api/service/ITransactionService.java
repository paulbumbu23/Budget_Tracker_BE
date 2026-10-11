package com.portofolio.budget_api.service;

import com.portofolio.budget_api.model.dto.request.MonthlyReportRequest;
import com.portofolio.budget_api.model.dto.request.TransactionRequest;
import com.portofolio.budget_api.model.dto.response.MonthlyReportResponse;
import com.portofolio.budget_api.model.dto.response.TransactionResponse;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionService {
    TransactionResponse addTransaction(Long userId, TransactionRequest transactionRequest);
    List<TransactionResponse> getAllTransactionsByUserId(Long userId);
    void deleteTransaction (Long transactionId);
    TransactionResponse updateTransaction(Long transactionId, TransactionRequest transactionRequest);
    MonthlyReportResponse getMonthlyReport(Long userId, MonthlyReportRequest monthlyReportRequest);
}
