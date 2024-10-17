package com.telusko.springSecurityEx.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.springSecurityEx.model.Card;

@RestController
public class hellocontroller {
	
	

@GetMapping("/")
    public String greet(HttpServletRequest request) {

        return "Welcome to Telusko "+request.getSession().getId();
    }

}