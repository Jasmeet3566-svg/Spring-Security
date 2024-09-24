package com.telusko.springSecurityEx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.telusko.springSecurityEx.model.Users;
import com.telusko.springSecurityEx.service.UserService;

public class UserController {
	
	@Autowired
	private UserService service;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@PostMapping("/register")//we are submitting data from client
	public Users register(@RequestBody Users users) {
		
		users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
		return  service.register(users);
		
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users users) {
		return service.verify(users);
	}
}
