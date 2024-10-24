package com.praj.hotel.UserService.dto;

import java.util.ArrayList;
import java.util.List;

import com.praj.hotel.UserService.model.Rating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private String userId;

	private String userName;

	private String email;

	private String about;
	
	private String working;

	private List<Rating> ratings = new ArrayList<>();

}
