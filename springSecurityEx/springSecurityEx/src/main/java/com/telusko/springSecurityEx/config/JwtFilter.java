package com.telusko.springSecurityEx.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.telusko.springSecurityEx.service.JWTService;
import com.telusko.springSecurityEx.service.MyUserdetailservice;

import ch.qos.logback.core.Context;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	ApplicationContext context;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		String tokken = null;
		String username = null;
		
		if(authHeader != null &&  authHeader.startsWith("Bearer ")) {
			tokken = authHeader.substring(7);
			username = jwtService.extractUserName(tokken);
			
        }
		
		if(username !=  null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = context.getBean(MyUserdetailservice.class).loadUserByUsername(username);
			
			if(jwtService.validateToken(tokken,userDetails)) {
				UsernamePasswordAuthenticationToken authtoken 
				=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
				authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authtoken);
			}
		}
		filterChain.doFilter(request, response);
    }
	
}