package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentRole;
import com.example.demo.repository.StudentRoleRepository;
import com.example.demo.service.RoleService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private StudentRoleRepository studentRoleRepository;


    // ===== LOGIN =====

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }


    // ===== REGISTER FORM =====

    @GetMapping("/register")
    public String registerForm(Model model){

        model.addAttribute(
                "student",
                new Student()
        );

        return "register";
    }


    // ===== REGISTER SAVE =====

    @PostMapping("/register")
    public String register(
            @ModelAttribute Student s
    ){

        Student saved =
                studentService.save(s);

        Role role =
                roleService.findByName("STUDENT");

        StudentRole sr =
                new StudentRole();

        sr.setStudentId(
                saved.getStudentId()
        );

        sr.setRoleId(
                role.getRoleId()
        );

        studentRoleRepository.save(sr);

        return "redirect:/login";
    }

}