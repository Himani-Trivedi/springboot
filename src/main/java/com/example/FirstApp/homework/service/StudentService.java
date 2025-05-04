package com.example.FirstApp.homework.service;

import com.example.FirstApp.homework.entity.Student;
import com.example.FirstApp.homework.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student addStudent(Student student){
        student.getAdminRecord().setStudent(student);
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student, Long id){
        Student studentUpdate = studentRepository.findById(id).orElse(null);

        if(studentUpdate == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such student exist");
        }

        studentUpdate.setName(student.getName());
        studentUpdate.setAddress(student.getAddress());
        studentUpdate.getAdminRecord().setFees(student.getAdminRecord().getFees());
        studentUpdate.setSubjectSet(student.getSubjectSet());
        studentUpdate.setProfessorSet(student.getProfessorSet());

        studentRepository.save(studentUpdate);
        return studentUpdate;
    }

    public Student deleteStudent(Long id){
        Student studentDelete = studentRepository.findById(id).orElse(null);
        if(studentDelete == null){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"no such student");
        }

        studentRepository.deleteById(id);
        return studentDelete;
    }
}
