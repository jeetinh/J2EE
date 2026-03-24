package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private EnrollmentRepository enrollRepo;



    // HOME

    @GetMapping("/")
    public String home(Model model){

        model.addAttribute(
                "list",
                courseRepo.findAll()
        );

        return "home";
    }



    // ENROLL

    @GetMapping("/enroll/{id}")
    public String enroll(
            @PathVariable Long id
    ){

        String username =
                SecurityUtil.getUsername();

        Student s =
                studentRepo
                        .findByUsername(username)
                        .orElseThrow();

        Enrollment e =
                new Enrollment();

        e.setStudentId(
                s.getStudentId()
        );

        e.setCourseId(id);

        enrollRepo.save(e);

        return "redirect:/";
    }



    // MY COURSES

    @GetMapping("/mycourses")
    public String myCourses(
            Model model
    ){

        String username =
                SecurityUtil.getUsername();

        Student s =
                studentRepo
                        .findByUsername(username)
                        .orElseThrow();

        List<Enrollment> list =
                enrollRepo
                        .findByStudentId(
                                s.getStudentId()
                        );

        model.addAttribute(
                "list",
                list
        );

        return "mycourses";
    }

}