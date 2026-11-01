package com.portofolio.budget_api.scheduler;

import com.portofolio.budget_api.model.dto.request.MonthlyReportRequest;
import com.portofolio.budget_api.model.dto.response.MonthlyReportResponse;
import com.portofolio.budget_api.model.entity.User;
import com.portofolio.budget_api.repository.UserRepository;
import com.portofolio.budget_api.service.IEmailService;
import com.portofolio.budget_api.service.ITransactionService;
import com.portofolio.budget_api.service.impl.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MonthlyReportScheduler {

    private final UserRepository userRepository;
    private final ITransactionService transactionService;
    private final IEmailService emailService;


    @Scheduled(cron = "0 0 8 1 * ?")
    public void generateAndSendMonthlyReports() {
        log.info("Starting generating monthly reports...");

        YearMonth lastMonth = YearMonth.now().minusMonths(1);
        MonthlyReportRequest reportRequest = new MonthlyReportRequest(
                lastMonth.getYear(),
                lastMonth.getMonthValue()
        );

        List<User> users = userRepository.findAll();

        for (User user : users) {
            try {
                MonthlyReportResponse report = transactionService.getMonthlyReport(user.getEmail(), reportRequest);


                if (report.getTotalIncomeRON().signum() == 0 && report.getTotalExpenseRON().signum() == 0) {
                    continue;
                }

                String emailBody = String.format(
                        "Hello,\n\nThis is your financial report for the month %d/%d:\n\n" +
                                "Total INCOME: %.2f RON\n" +
                                "Total EXPENSE: %.2f RON\n" +
                                "Remaining Balance (RON): %.2f RON\n" +
                                "Remaining Balance (EUR): %.2f EUR\n" +
                                "Remaining Balance (USD): %.2f USD\n\n" +
                                "I wish you luck this month!\nBudgetApp Team",
                        report.getMonth(), report.getYear(),
                        report.getTotalIncomeRON(), report.getTotalExpenseRON(),
                        report.getBalanceRON(), report.getBalanceEUR(), report.getBalanceUSD()
                );

                emailService.sendSimpleEmail(
                        user.getEmail(),
                        "Budget Report - " + lastMonth.getMonth(),
                        emailBody
                );

                log.info("Email send succesfully to {}", user.getEmail());

            } catch (Exception e) {
                log.error("Error sending this email to {}: {}", user.getEmail(), e.getMessage());
            }
        }

        log.info("Generating reports finished!");
    }
}