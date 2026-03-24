package com.example.demo.config;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService
        implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private StudentRoleRepository srRepo;

    @Autowired
    private RoleRepository roleRepo;



    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {

        Student s =
                studentRepo
                        .findByUsername(username)
                        .orElseThrow();

        StudentRole sr =
                srRepo.findAll()
                        .stream()
                        .filter(x ->
                                x.getStudentId()
                                        .equals(
                                                s.getStudentId()
                                        )
                        )
                        .findFirst()
                        .orElseThrow();

        Role r =
                roleRepo.findById(
                        sr.getRoleId()
                ).orElseThrow();

        return new CustomUserDetails(
                s.getUsername(),
                s.getPassword(),
                r.getName()
        );
    }
}