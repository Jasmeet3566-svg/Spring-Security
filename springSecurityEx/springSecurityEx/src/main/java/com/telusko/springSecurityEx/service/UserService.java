package com.telusko.springSecurityEx.service;

import com.telusko.springSecurityEx.model.ERole;
import com.telusko.springSecurityEx.model.Users;
import com.telusko.springSecurityEx.model.userRole;
import com.telusko.springSecurityEx.Repo.UserRepo;
import com.telusko.springSecurityEx.Repo.roleRepository;

import java.util.HashSet;
import java.util.Set;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepo repo;
    
    @Autowired
    private roleRepository roleRepository;
    
    


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        
        userRole defaultRole = roleRepository.findByName(ERole.ROLE_PENDING_APPROVAL)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Set<userRole> roles = new HashSet<>();
        roles.add(defaultRole);
        user.setRoles(roles);
        
        
        repo.save(user);
        return user;
    }

    public String verify(Users user) {
    	System.out.println(user);
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        
        
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }
    
    
    
    
    public Users assignRole(Long userId, ERole newRole) {
    	
    	String userId1 =userId.toString();
        Users user = repo.findByUsername(userId1);
               

        userRole role = roleRepository.findByName(newRole)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRoles().clear(); // Clear existing roles
        user.getRoles().add(role); // Add new role

        return repo.save(user);
    }
    
}