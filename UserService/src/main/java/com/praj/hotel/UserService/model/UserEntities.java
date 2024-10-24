package com.praj.hotel.UserService.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "users")
public class UserEntities {

	@Id
	@Column(name= "Id")
	private String userId;

	@Column(name= "Name", length = 20)
	private String userName;

	@Column(name= "Email")
	private String email;

	@Column(name= "About")
	private String about;
	
	@Column(name = "Working")
	private String working;

	@Transient //It's use for , if you don't want to store in database
	private List<Rating> ratings = new ArrayList<>();

}
