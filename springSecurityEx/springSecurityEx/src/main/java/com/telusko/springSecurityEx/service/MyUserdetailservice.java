package com.telusko.springSecurityEx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.telusko.springSecurityEx.Repo.UserRepo;
import com.telusko.springSecurityEx.model.UserPrincipal;
import com.telusko.springSecurityEx.model.Users;


@Service
public class MyUserdetailservice implements UserDetailsService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = repo.findByUsername(username);
		
		if(users == null) {
			System.out.println("user not foumd");
			throw new UsernameNotFoundException(username);
			
		}
		return new UserPrincipal(users);
	}
	

}
