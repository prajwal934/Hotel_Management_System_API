package com.praj.hotel.UserService.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.praj.hotel.UserService.dto.UserDto;
import com.praj.hotel.UserService.model.UserEntities;
import com.praj.hotel.UserService.utility.ResponseStructure;

public interface UserService  {
	
	public ResponseEntity<ResponseStructure<UserEntities>> saveUser(UserDto userDto);
	
	public ResponseEntity<ResponseStructure<UserEntities>> findByUserId(String userId);
	
	public ResponseEntity<ResponseStructure<UserEntities>> updateByUserId(String userId , UserDto userDto);
	
	public ResponseEntity<ResponseStructure<UserEntities>> deleteByUserId(String userId);
	
	public ResponseEntity<ResponseStructure<List<UserEntities>>> findAllUsers();
	

}
