package com.portofolio.budget_api.service.impl;

import com.portofolio.budget_api.model.converter.TransactionConverter;
import com.portofolio.budget_api.model.dto.request.TransactionRequest;
import com.portofolio.budget_api.model.dto.response.TransactionResponse;
import com.portofolio.budget_api.model.entity.Transaction;
import com.portofolio.budget_api.model.entity.User;
import com.portofolio.budget_api.repository.TransactionRepository;
import com.portofolio.budget_api.repository.UserRepository;
import com.portofolio.budget_api.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final TransactionConverter transactionConverter;

    @Override
    public TransactionResponse addTransaction(Long userId, TransactionRequest transactionRequest){
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User with id" +userId + "not found"));
        Transaction transaction = transactionConverter.toEntity(transactionRequest);
        transaction.setUser(user);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionConverter.toResponse(savedTransaction);
    }

    @Override
    public List<TransactionResponse> getAllTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserId(userId).stream()
                .map(transactionConverter::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        if (!transactionRepository.existsById(transactionId)) {
            throw new RuntimeException("Transaction not found!");
        }
        transactionRepository.deleteById(transactionId);
    }
}
