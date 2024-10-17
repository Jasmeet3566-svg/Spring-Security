package com.telusko.springSecurityEx.controller;

import com.telusko.springSecurityEx.model.ERole;
import com.telusko.springSecurityEx.model.Users;
import com.telusko.springSecurityEx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @PutMapping("/assign-role/{userId}")
    public Users assignRole(@PathVariable Long userId, @RequestParam ERole newRole) {
        return userService.assignRole(userId, newRole);
    	 
    }
}
