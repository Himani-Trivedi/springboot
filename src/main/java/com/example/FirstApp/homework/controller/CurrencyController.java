package com.example.FirstApp.homework.controller;


import com.example.FirstApp.homework.service.CurrencyConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CurrencyController {

    private CurrencyConverterService currencyConverterService;

    public CurrencyController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }

    @GetMapping("/currencies")
    public String convertCurrency(@RequestParam String from, @RequestParam String to, @RequestParam Double amount){
        currencyConverterService.storeCurrencyList();
        return "Hello";
    }

    @GetMapping("/convertCurrency")
    public String check(@RequestParam String from, @RequestParam String to, @RequestParam Double amount){
        Double convertedValue = currencyConverterService.convertCurrency(from, to, amount);
        return convertedValue.toString();
    }
}
