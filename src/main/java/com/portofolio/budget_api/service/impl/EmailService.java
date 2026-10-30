package com.portofolio.budget_api.service.impl;

import com.portofolio.budget_api.service.IEmailService;
import com.portofolio.budget_api.service.ITransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {
    private final JavaMailSender mailSender;

    public void sendSimpleEmail(String toMail, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toMail);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("paul.bumbu23@gmail.com");

        mailSender.send(message);
    }
}
