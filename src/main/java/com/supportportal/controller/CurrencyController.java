package com.supportportal.controller;

import com.supportportal.model.Currency;
import com.supportportal.service.impl.CurrencyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("currency")
public class CurrencyController {
    private CurrencyServiceImpl currencyService;

    public CurrencyController(CurrencyServiceImpl currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public ResponseEntity<List<Currency>> getAll() {
        return new ResponseEntity<>(this.currencyService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Currency> getById(@PathVariable("id") Long currencyId) {
        return new ResponseEntity<>(this.currencyService.getById(currencyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Currency> insert(@RequestBody Currency currency) {
        return new ResponseEntity<>(this.currencyService.insert(currency), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Currency> update(@PathVariable("id") Long currencyId, @RequestBody Currency currency) {
        return new ResponseEntity<>(this.currencyService.update(currencyId, currency),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Currency> deleteById(@PathVariable("id") Long currencyId) {
        this.currencyService.deleteById(currencyId);
       return new ResponseEntity<>( HttpStatus.OK);
    }
}
