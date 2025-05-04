package com.example.FirstApp.homework.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

    @ManyToOne
    @JoinColumn
    private Professor professor;

    @ManyToMany(mappedBy = "subjectSet")
    private Set<Student> studentSet;
}
