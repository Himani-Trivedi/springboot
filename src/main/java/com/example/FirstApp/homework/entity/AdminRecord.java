package com.example.FirstApp.homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AdminRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double fees;

    @OneToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Student student;
}
