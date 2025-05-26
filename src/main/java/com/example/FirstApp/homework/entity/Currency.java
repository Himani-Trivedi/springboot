package com.example.FirstApp.homework.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Currency {

    @Id
    private String code;

    private String symbol;

    private String name;

    private String symbol_native;

    private Integer decimal_digits;

    private Integer rounding;

    private String name_plural;

    private String type;
}
