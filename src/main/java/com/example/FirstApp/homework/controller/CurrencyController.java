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

    @GetMapping("/convertCurrency")
    public String convertCurrency(@RequestParam String from, @RequestParam String to, @RequestParam Double amount){
        log.info(from + to + amount);
//        currencyConverterService.storeCurrencyList();
        return "Hello";
    }

    @GetMapping("/check")
    public String check(@PathVariable(required = false) String from, @PathVariable(required = false) String to, @PathVariable(required = false) Double amount){
        return "Hello";
    }
}
