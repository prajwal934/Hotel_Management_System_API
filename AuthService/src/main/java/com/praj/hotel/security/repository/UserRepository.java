package com.praj.hotel.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.praj.hotel.security.model.UserCredential;

@Repository
public interface UserRepository extends JpaRepository<UserCredential, Integer> {

	Optional<UserCredential> findByUserName(String userName);

}
