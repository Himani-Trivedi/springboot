package com.example.FirstApp.homework.repository;

import com.example.FirstApp.homework.entity.AdminRecord;
import com.example.FirstApp.homework.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRecordRepository extends JpaRepository<AdminRecord,Long> {

}
