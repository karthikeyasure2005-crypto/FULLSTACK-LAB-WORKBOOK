package com.fsad;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student {

    @Value("102")
    private int studentId;

    @Value("Karthikeya")
    private String name;

    private String course;
    private int year;

    // Constructor Injection
    public Student(@Value("Spring Core") String course,
                   @Value("3") int year) {
        this.course = course;
        this.year = year;
    }

    public void display() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Year: " + year);
    }
}
