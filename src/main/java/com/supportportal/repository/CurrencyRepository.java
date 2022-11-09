package com.supportportal.repository;

import com.supportportal.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Long> {
    Currency deleteCurrencyById(Long currencyId);
}
