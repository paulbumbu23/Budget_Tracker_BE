package com.portofolio.budget_api.service.impl;

import com.portofolio.budget_api.service.IExchangeRateService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeRateService implements IExchangeRateService {

    private static final String API_URL = "https://open.er-api.com/v6/latest/";

    public BigDecimal getExchangeRate(String fromCurrency, String toCurrency) {
        if (fromCurrency.equalsIgnoreCase(toCurrency)) {
            return BigDecimal.ONE;
        }
        RestTemplate restTemplate = new RestTemplate();
        try {
            String url = API_URL + fromCurrency.toUpperCase() ;
            Map<String,Object> response = restTemplate.getForObject(url, Map.class);

            Map<String, Double> rates = (Map<String, Double>) response.get("rates");

            Double rate = rates.get(toCurrency.toUpperCase());
            return BigDecimal.valueOf(rate);
    }catch (Exception e){
            throw new RuntimeException("Error at getting Exchange Rate " + fromCurrency + " -> " + toCurrency);    }
    }
}
