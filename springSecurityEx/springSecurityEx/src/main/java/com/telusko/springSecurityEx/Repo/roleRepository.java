package com.telusko.springSecurityEx.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telusko.springSecurityEx.model.ERole;
import com.telusko.springSecurityEx.model.userRole;

public interface roleRepository extends JpaRepository<userRole, Integer> {
	
	Optional<userRole> findByName(ERole name);
    
}
