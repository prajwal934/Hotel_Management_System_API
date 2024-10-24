package com.praj.hotel.HotelService.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.praj.hotel.HotelService.dto.HotelDTO;
import com.praj.hotel.HotelService.model.Hotel;
import com.praj.hotel.HotelService.utility.ResponseStructure;

public interface HotelService {

	public ResponseEntity<ResponseStructure<Hotel>> createHotel(HotelDTO hotelDto);
	public ResponseEntity<ResponseStructure<List<Hotel>>> getAllHotels();
	public ResponseEntity<ResponseStructure<Hotel>> findByHotelId(String hotelId);
	
}
