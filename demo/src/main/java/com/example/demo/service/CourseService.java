package com.example.demo.service;



import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CourseService {

    @Autowired
    private CourseRepository repo;

    public Page<Course> getAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Page<Course> search(String keyword, Pageable pageable) {
        return repo.findByNameContaining(keyword, pageable);
    }
    public List<Course> getAllList(){
        return repo.findAll();
    }
    public Course save(Course c) {
        return repo.save(c);
    }

    public Course getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}