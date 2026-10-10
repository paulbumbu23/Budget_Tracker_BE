package com.portofolio.budget_api.service;

import com.portofolio.budget_api.model.dto.request.TransactionRequest;
import com.portofolio.budget_api.model.dto.response.TransactionResponse;

import java.util.List;

public interface ITransactionService {
    TransactionResponse addTransaction(Long userId, TransactionRequest transactionRequest);
    List<TransactionResponse> getAllTransactionsByUserId(Long userId);
    void deleteTransaction (Long transactionId);
}
