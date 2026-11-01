package com.portofolio.budget_api.service;

import java.math.BigDecimal;

public interface IExchangeRateService {
    BigDecimal getExchangeRate(String fromCurrency, String toCurrency);
}
