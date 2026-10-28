package com.portofolio.budget_api.controller;

import com.portofolio.budget_api.model.dto.request.MonthlyReportRequest;
import com.portofolio.budget_api.model.dto.request.TransactionRequest;
import com.portofolio.budget_api.model.dto.response.MonthlyReportResponse;
import com.portofolio.budget_api.model.dto.response.TransactionResponse;
import com.portofolio.budget_api.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> addTransaction(
            @RequestBody TransactionRequest request,
            Principal principal) {

        TransactionResponse response = transactionService.addTransaction(principal.getName(), request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getUserTransactions(Principal principal) {
        List<TransactionResponse> responses = transactionService.getAllTransactionsByUserEmail(principal.getName());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/report")
    public ResponseEntity<MonthlyReportResponse> getMonthlyReport(
            @ModelAttribute MonthlyReportRequest request,
            Principal principal) {

        return ResponseEntity.ok(transactionService.getMonthlyReport(principal.getName(), request));
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> updateTransaction(
            @PathVariable Long transactionId,
            @RequestBody TransactionRequest request) {

        return ResponseEntity.ok(transactionService.updateTransaction(transactionId, request));
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long transactionId) {

        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok("Transaction deleted successfully!");
    }
}