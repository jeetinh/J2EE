package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CourseService courseService;


    // LIST

    @GetMapping("/course")
    public String list(Model model) {

        List<Course> list =
                courseService.getAllList();

        model.addAttribute("list", list);

        return "admin/course";
    }


    // ADD FORM

    @GetMapping("/admin/add")
    public String add(Model model){

        model.addAttribute(
                "course",
                new Course()
        );

        return "admin/add";
    }


    // SAVE

    @PostMapping("/admin/save")
    public String save(Course c){

        courseService.save(c);

        return "redirect:/home";
    }


    // EDIT FORM

    @GetMapping("/edit/{id}")
    public String editForm(
            @PathVariable Long id,
            Model model
    ) {

        Course c =
                courseService.getById(id);

        model.addAttribute("course", c);

        return "admin/edit";
    }


    // DELETE

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id
    ) {

        courseService.delete(id);

        return "redirect:/admin/course";
    }

}