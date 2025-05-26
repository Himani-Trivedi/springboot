package com.example.FirstApp.homework.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class CurrencyDTO {

   private Map<String,Currency> data;
}
