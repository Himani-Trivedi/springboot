package com.example.FirstApp.homework.repository;

import com.example.FirstApp.homework.entity.ConversionLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversionLogsRepository extends JpaRepository<ConversionLogs, Long> {
}
