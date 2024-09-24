package com.telusko.springSecurityEx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.telusko.springSecurityEx.service.JWTService;

@Configuration
@EnableWebSecurity
public class SpringConfig {
	
	@Autowired
	public UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtfilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {//this return this object of SecurityFilterChain
		
		return http.csrf(customizer -> customizer.disable())//disable csrf
		.authorizeHttpRequests(request -> request.requestMatchers("register","login").permitAll().anyRequest().authenticated()) //apply our own authentication
		//http.formLogin(Customizer.withDefaults());//login with default form
		.httpBasic(Customizer.withDefaults())//for postman
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class)
		
		.build();
	}
	
	/*
	 * @Bean public UserDetailsService userDetailsService() {
	 * 
	 * UserDetails user1 = org.springframework.security.core.userdetails.User
	 * .withDefaultPasswordEncoder() .username("jassi") .password("JA12")
	 * .roles("USER") .build();
	 * 
	 * return new InMemoryUserDetailsManager(user1);
	 * 
	 * }
	 */
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		provider.setUserDetailsService(userDetailsService);
		
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();
		
	}
}
