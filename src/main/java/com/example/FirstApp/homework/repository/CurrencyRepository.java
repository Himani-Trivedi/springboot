package com.example.FirstApp.homework.repository;

import com.example.FirstApp.homework.entity.Currency;
import com.example.FirstApp.homework.entity.CurrencyDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
