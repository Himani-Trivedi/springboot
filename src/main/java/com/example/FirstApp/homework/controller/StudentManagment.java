package com.example.FirstApp.homework.controller;

import com.example.FirstApp.homework.entity.AdminRecord;
import com.example.FirstApp.homework.entity.Student;
import com.example.FirstApp.homework.repository.StudentRepository;
import com.example.FirstApp.homework.service.AdminService;
import com.example.FirstApp.homework.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class StudentManagment {

    private StudentService studentService;
    private AdminService adminService;

    public StudentManagment(StudentService studentService, AdminService adminService) {
        this.studentService = studentService;
        this.adminService = adminService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("/add/student")
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @PutMapping("/student/update/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable Long id){
        return studentService.updateStudent(student,id);
    }

    @DeleteMapping("/student/delete/{id}")
    public Student deleteStudent(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }


    @GetMapping("/adminRecord")
    public List<AdminRecord> getAllRecords(){
        return adminService.getAllRecords();
    }

    @PostMapping("/add/adminRecord")
    public AdminRecord addStudent(@RequestBody AdminRecord adminRecord){
        return adminService.addRecord(adminRecord);
    }
}
