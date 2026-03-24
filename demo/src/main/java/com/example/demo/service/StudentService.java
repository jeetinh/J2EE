package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public Student save(Student s){
        return repo.save(s);
    }

    public Student findByUsername(String username){
        return repo.findByUsername(username).orElse(null);
    }

}