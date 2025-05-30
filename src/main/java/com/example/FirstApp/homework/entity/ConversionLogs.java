package com.example.FirstApp.homework.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class ConversionLogs extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    private Currency from;

    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    private Currency to;

    private Double amount;

    private Double convertedValue;
}
