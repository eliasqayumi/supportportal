package com.supportportal.service.impl;

import com.supportportal.model.Currency;
import com.supportportal.repository.CurrencyRepository;
import com.supportportal.service.CurrencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<Currency> getAll() {
        return this.currencyRepository.findAll();
    }

    @Override
    public Currency insert(Currency currency) {
        return this.currencyRepository.save(currency);
    }

    @Override
    public Currency update(Long currencyId, Currency currency) {
        Currency updateCurrency = this.currencyRepository.getById(currencyId);
        updateCurrency.setCurrency(currency.getCurrency());
        return this.currencyRepository.save(updateCurrency);
    }

    @Override
    public void deleteById(Long currencyId) {

    this.currencyRepository.deleteById(currencyId);
    }

    @Override
    public Currency getById(Long currencyId) {
        return this.currencyRepository.getById(currencyId);
    }
}
