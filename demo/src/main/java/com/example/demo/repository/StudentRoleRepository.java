package com.example.demo.repository;

import com.example.demo.entity.StudentRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRoleRepository extends JpaRepository<StudentRole, Long> {

    List<StudentRole> findByStudentId(Long studentId);

}