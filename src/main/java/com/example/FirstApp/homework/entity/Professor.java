package com.example.FirstApp.homework.entity;


import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "professorSet")
    private Set<Student> studentSet;

    @OneToMany(mappedBy = "professor")
    private Set<Subject> subjectSet;
}
