package com.portofolio.budget_api.service;

public interface IEmailService {
    void sendSimpleEmail(String to, String subject, String text);
}
