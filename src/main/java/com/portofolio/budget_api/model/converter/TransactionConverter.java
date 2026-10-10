package com.portofolio.budget_api.model.converter;

import com.portofolio.budget_api.model.dto.request.TransactionRequest;
import com.portofolio.budget_api.model.dto.response.TransactionResponse;
import com.portofolio.budget_api.model.entity.Transaction;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    public Transaction toEntity(TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setCategory(transactionRequest.getCategory());
        transaction.setDescription(transactionRequest.getDescription());
        transaction.setTransactionDate(transactionRequest.getTransactionDate());
        transaction.setType(transactionRequest.getType());
        return transaction;
    }

    public TransactionResponse toResponse(Transaction transaction) {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setId(transaction.getId());
        transactionResponse.setAmount(transaction.getAmount());
        transactionResponse.setCategory(transaction.getCategory());
        transactionResponse.setDescription(transaction.getDescription());
        transactionResponse.setTransactionDate(transaction.getTransactionDate());
        transactionResponse.setTransactionType(transaction.getType());

        if (transaction.getUser() != null) {
            transactionResponse.setUserId(transaction.getUser().getId());
        }
        return transactionResponse;
    }
}
