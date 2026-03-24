package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/home")
    public String home(Model model,
                       Integer page) {

        int currentPage = (page == null) ? 0 : page;

        Page<Course> coursePage =
                courseService.getAll(
                        PageRequest.of(currentPage, 5)
                );

        model.addAttribute("coursePage", coursePage);
        model.addAttribute("currentPage", currentPage);

        return "home";
    }

}