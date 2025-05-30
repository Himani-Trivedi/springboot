package com.example.FirstApp.homework.service;

import com.example.FirstApp.homework.entity.ConversionLogs;
import com.example.FirstApp.homework.entity.Currency;
import com.example.FirstApp.homework.entity.CurrencyDTO;
import com.example.FirstApp.homework.repository.ConversionLogsRepository;
import com.example.FirstApp.homework.repository.CurrencyRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class CurrencyConverterService {

    final private String base_url = "https://api.freecurrencyapi.com/v1/latest?";
    final private String api_key = "fca_live_BABCu1KSDisFzhVIg3wWC62J99yg62dolr0DoGxM";

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private ConversionLogsRepository conversionLogsRepository;

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

    public Double convertCurrency(String from, String to, Double amount) {
        Optional<Currency> fromCurrency = currencyRepository.findByCode(from);
        Optional<Currency> toCurrency = currencyRepository.findByCode(to);

        if (fromCurrency.isEmpty() || to.isEmpty()) {
            log.error("Currency not found error");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Currency not found");
        }

        Double convertedValue = amount * getRate(from, to);
        log.info(from + " " + to + " " + amount + " " + convertedValue);

        ConversionLogs conversionLogs = ConversionLogs.builder()
                .from(fromCurrency.get())
                .to(toCurrency.get())
                .amount(amount)
                .convertedValue(convertedValue)
                .build();
        conversionLogsRepository.save(conversionLogs);
        return convertedValue;
    }

    private Double getRate(String from, String to) {
        try {
            String baseUrl = "https://api.freecurrencyapi.com/v1/latest?apikey=" + api_key + "&currencies=" + to + "&base_currency=" + from;

            RestClient restClient = RestClient.builder()
                    .baseUrl(baseUrl)
                    .build();

            String response = restClient.get()
                    .retrieve()
                    .onStatus(HttpStatusCode::is3xxRedirection, (request,clientHttpResponse) -> {
                        log.error("Error in conversion " + clientHttpResponse.getStatusCode());
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "Error in conversion " + clientHttpResponse.getStatusCode());
                    })
                    .body(String.class);

            log.info("query response "  + response);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String,Object> responseMap= objectMapper.readValue(response, new TypeReference<Map<String,Object>>() {});
            log.info("response map " + responseMap);
            Map<String,Object> value = (Map<String, Object>) responseMap.get("data");
            Double rate = Double.valueOf(value.get(to).toString());
            return rate;
        } catch (Exception e) {
            log.error("Error in conversion " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error in conversion " + e.getMessage());
        }
    }
}
