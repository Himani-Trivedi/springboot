package com.example.FirstApp.homework.repository;

import com.example.FirstApp.homework.entity.Currency;
import com.example.FirstApp.homework.entity.CurrencyDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findBySymbol(String symbol);

    Optional<Currency> findByCode(String to);
}
