package com.example.FirstApp.homework.service;

import com.example.FirstApp.homework.entity.Currency;
import com.example.FirstApp.homework.entity.CurrencyDTO;
import com.example.FirstApp.homework.repository.CurrencyRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CurrencyConverterService {

    final private String base_url = "https://api.freecurrencyapi.com/v1/latest?";
    final private String api_key = "fca_live_BABCu1KSDisFzhVIg3wWC62J99yg62dolr0DoGxM";

    @Autowired
    private CurrencyRepository currencyRepository;

    public void storeCurrencyList() {
        try {
            String url = "https://api.freecurrencyapi.com/v1/currencies?apikey=" + api_key;

            RestClient restClient = RestClient
                    .builder()
                    .baseUrl(url)
                    .build();

            String response = restClient
                    .get()
                    .retrieve()
                    .body(String.class); //return type application/json

            log.info(response.toString());

            ObjectMapper objectMapper = new ObjectMapper();
            CurrencyDTO currencyDTO = objectMapper.readValue(response, CurrencyDTO.class);

            for (Map.Entry<String, Currency> entry : currencyDTO.getData().entrySet()) {
                log.info(entry.getKey() + " " + entry.getValue());
                currencyRepository.save(entry.getValue());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
