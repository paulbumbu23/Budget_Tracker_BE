package com.portofolio.budget_api.service.impl;

import com.portofolio.budget_api.exception.ResourceNotFoundException;
import com.portofolio.budget_api.model.converter.TransactionConverter;
import com.portofolio.budget_api.model.dto.request.MonthlyReportRequest;
import com.portofolio.budget_api.model.dto.request.TransactionRequest;
import com.portofolio.budget_api.model.dto.response.MonthlyReportResponse;
import com.portofolio.budget_api.model.dto.response.TransactionResponse;
import com.portofolio.budget_api.model.entity.Transaction;
import com.portofolio.budget_api.model.entity.User;
import com.portofolio.budget_api.model.enums.TransactionType;
import com.portofolio.budget_api.repository.TransactionRepository;
import com.portofolio.budget_api.repository.UserRepository;
import com.portofolio.budget_api.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
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
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with id " + userId + " not found"));
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
            throw new ResourceNotFoundException("Transaction with id " + transactionId + " not found");
        }
        transactionRepository.deleteById(transactionId);
    }

    @Override
    public TransactionResponse updateTransaction(Long transactionId, TransactionRequest transactionRequest) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction with id " + transactionId + " not found"));

        transaction.setAmount(transactionRequest.getAmount());
        transaction.setDescription(transactionRequest.getDescription());
        transaction.setTransactionDate(transactionRequest.getTransactionDate());
        transaction.setCategory(transactionRequest.getCategory());
        transaction.setType(transactionRequest.getType());

        Transaction updatedTransaction = transactionRepository.save(transaction);
        return transactionConverter.toResponse(updatedTransaction);
    }

    @Override
    public MonthlyReportResponse getMonthlyReport(Long userId, MonthlyReportRequest request) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User with ID " + userId + " not found!");
        }

        YearMonth yearMonth = YearMonth.of(request.getYear(), request.getMonth());
        LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = yearMonth.atEndOfMonth().atTime(23, 59, 59);

        List<Transaction> transactions = transactionRepository
                .findByUserIdAndTransactionDateBetween(userId, startOfMonth, endOfMonth);

        BigDecimal totalIncome = transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpense = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance = totalIncome.subtract(totalExpense);

        return new MonthlyReportResponse(
                request.getYear(),
                request.getMonth(),
                totalIncome,
                totalExpense,
                balance
        );
    }
}
