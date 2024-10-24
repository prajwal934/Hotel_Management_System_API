package com.praj.hotel.security.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.praj.hotel.security.dto.AuthRequest;
import com.praj.hotel.security.model.UserCredential;
import com.praj.hotel.security.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

	private AuthService authService;
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public String addUser(@RequestBody UserCredential userCredential) {
		return authService.addUser(userCredential);
	}
	

	@PostMapping("/token")
	public String getToken(@RequestBody AuthRequest authRequest) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		
		if(authenticate.isAuthenticated()) {
			return authService.generateToken(authRequest.getUserName());
		} else {
			throw new RuntimeException(" invalid access");
		}
	}
	
	@GetMapping("/validate")
	public String validateToken(@RequestParam("token") String token) {
		authService.validateToken(token);
	return "Token is Valid";
	}
}
