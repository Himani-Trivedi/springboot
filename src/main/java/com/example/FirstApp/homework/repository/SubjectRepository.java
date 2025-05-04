package com.example.FirstApp.homework.repository;

import com.example.FirstApp.homework.entity.AdminRecord;
import com.example.FirstApp.homework.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {

}
