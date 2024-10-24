package com.praj.hotel.HotelService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.praj.hotel.HotelService.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {

}
