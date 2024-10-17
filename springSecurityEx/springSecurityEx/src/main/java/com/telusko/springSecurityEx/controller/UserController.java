package com.telusko.springSecurityEx.controller;

import com.telusko.springSecurityEx.Repo.CardRepo;
import com.telusko.springSecurityEx.model.Card;
import com.telusko.springSecurityEx.model.Users;
import com.telusko.springSecurityEx.service.CardService;
import com.telusko.springSecurityEx.service.UserService;

import java.util.List;

import org.hibernate.internal.util.beans.BeanInfoHelper.ReturningBeanInfoDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    CardRepo card; 


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public String register(@RequestBody Users user) {
    	service.register(user);
        return "ok" ;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public String login(@RequestBody Users user) {

        return service.verify(user);
    }
    
//    @Autowired
//    private CardService cardService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user")
    
    public List <Card> getCardsForUser(Authentication authentication) {
        String username = authentication.getName();
//        System.out.println("Authenticated user: " + username); // Log the username
//        // Fetch all cards for the user
//        List<Card> cards = cardService.getCardsByUsername(username);
//        System.out.println("Fetched cards: " + cards);
//        return ResponseEntity.ok(cards);
    	return card.findByUsername(username);
    }
}