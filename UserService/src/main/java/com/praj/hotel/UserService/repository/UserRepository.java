package com.praj.hotel.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.praj.hotel.UserService.model.UserEntities;

public interface UserRepository extends JpaRepository<UserEntities, String> {

}
