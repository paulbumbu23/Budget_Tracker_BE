package com.portofolio.budget_api.controller;

import com.portofolio.budget_api.model.dto.request.MonthlyReportRequest;
import com.portofolio.budget_api.model.dto.request.TransactionRequest;
import com.portofolio.budget_api.model.dto.response.MonthlyReportResponse;
import com.portofolio.budget_api.model.dto.response.TransactionResponse;
import com.portofolio.budget_api.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService transactionService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<TransactionResponse> addTransaction(
            @PathVariable Long userId,
            @RequestBody TransactionRequest request) {

        TransactionResponse response = transactionService.addTransaction(userId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionResponse>> getUserTransactions(@PathVariable Long userId) {

        List<TransactionResponse> responses = transactionService.getAllTransactionsByUserId(userId);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long transactionId) {

        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok("Transaction deleted successfully!");
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> updateTransaction(
            @PathVariable Long transactionId,
            @RequestBody TransactionRequest request) {

        return ResponseEntity.ok(transactionService.updateTransaction(transactionId, request));
    }

    @GetMapping("/user/{userId}/report")
    public ResponseEntity<MonthlyReportResponse> getMonthlyReport(
            @PathVariable Long userId,
            @ModelAttribute MonthlyReportRequest request) {

        return ResponseEntity.ok(transactionService.getMonthlyReport(userId, request));
    }
}