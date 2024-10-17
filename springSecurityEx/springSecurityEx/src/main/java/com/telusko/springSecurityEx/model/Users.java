
package com.telusko.springSecurityEx.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "register_user")
public class Users {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;
    
    private String username;
    
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String companyName;
    private String region;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<userRole> roles = new HashSet<>();
    
}

