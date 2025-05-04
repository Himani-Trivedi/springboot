package com.example.FirstApp.homework.service;


import com.example.FirstApp.homework.entity.AdminRecord;
import com.example.FirstApp.homework.repository.AdminRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private AdminRecordRepository adminRecordRepository;

    public AdminService(AdminRecordRepository adminRecordRepository) {
        this.adminRecordRepository = adminRecordRepository;
    }

    public List<AdminRecord> getAllRecords(){
        return adminRecordRepository.findAll();
    }

    public AdminRecord addRecord(AdminRecord record){
        return adminRecordRepository.save(record);
    }
}
