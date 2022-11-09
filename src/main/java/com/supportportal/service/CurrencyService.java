package com.supportportal.service;


import com.supportportal.model.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> getAll();
    Currency insert(Currency currency);
    Currency update(Long currencyId,Currency currency);
    void deleteById(Long currencyId);
    Currency getById(Long currencyId);
}