package com.praj.hotel.security.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.praj.hotel.security.exceptions.UserNotFoundException;
import com.praj.hotel.security.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUserName(username).map(CustomUserDetails::new)
				.orElseThrow(() -> new  UserNotFoundException("Authentication Failed, User not found with the given name"));
	}


	

}
