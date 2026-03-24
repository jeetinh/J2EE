package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    private int credits;

    private String lecturer;
    @Column(name = "category_id")

    private Long categoryId;

    public String getName()
    {
        return this.name;
    }
    public String getLecturer()
    {
        return this.lecturer;
    }
    public int getCredits(){
        return this.credits;
    }
}