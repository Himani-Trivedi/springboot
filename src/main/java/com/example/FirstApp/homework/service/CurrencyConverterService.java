package com.example.FirstApp.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CurrencyConverterService {

    final private String base_url = "https://api.freecurrencyapi.com/v1/latest?";
    final private String api_key = "fca_live_BABCu1KSDisFzhVIg3wWC62J99yg62dolr0DoGxM";

    public void storeCurrencyList() {
        try {
            String url = "https://api.freecurrencyapi.com/v1/currencies?apikey=" + api_key;

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);
            log.info(response);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
