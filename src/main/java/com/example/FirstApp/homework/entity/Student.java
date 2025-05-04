package com.example.FirstApp.homework.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private String Address;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Professor> professorSet;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Subject> subjectSet;

    @OneToOne(mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private AdminRecord adminRecord;
}
