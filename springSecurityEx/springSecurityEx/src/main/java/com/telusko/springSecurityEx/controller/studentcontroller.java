package com.telusko.springSecurityEx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.springSecurityEx.model.student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class studentcontroller {
	private List<student> students = new ArrayList<>(
            List.of(
                    new student(1, "Navin", 60),
                    new student(2, "Kiran", 65)
            ));


	@GetMapping("/students")
    public List<student> getStudents() {
        return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");


    }


    @PostMapping("/students")
    public student addStudent(@RequestBody student student) {
        students.add(student);
        return student;
    }

}
