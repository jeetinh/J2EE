package com.example.demo.service;

import com.example.demo.entity.Enrollment;
import com.example.demo.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository repo;

    public Enrollment save(Enrollment e) {
        return repo.save(e);
    }

    public List<Enrollment> getByStudent(Long studentId) {
        return repo.findByStudentId(studentId);
    }

}