package com.praj.hotel.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.praj.hotel.security.jwt.JwtService;
import com.praj.hotel.security.model.UserCredential;
import com.praj.hotel.security.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
	
//	public String addUser(UserCredential userCredential);
//
//	public String generateToken(String userName);
//	
//	public void validateToken(String token);
	
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private JwtService jwtService;
	
	public String addUser(UserCredential userCredential) {
		userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
		userRepository.save(userCredential);
		return "user added successfully"; 
	}
	
	public String generateToken(String userName) {
		return jwtService.generateToken(userName);
	}
	
	public void validateToken(String token) {
		jwtService.validateToken(token);
	}

}
