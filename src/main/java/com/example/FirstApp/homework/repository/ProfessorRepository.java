package com.example.FirstApp.homework.repository;

import com.example.FirstApp.homework.entity.Professor;
import com.example.FirstApp.homework.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}
