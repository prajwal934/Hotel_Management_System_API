package com.praj.hotel.HotelService.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.praj.hotel.HotelService.dto.HotelDTO;
import com.praj.hotel.HotelService.model.Hotel;
import com.praj.hotel.HotelService.service.HotelService;
import com.praj.hotel.HotelService.utility.ResponseStructure;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/hotels")
@AllArgsConstructor
public class HotelController {

	private HotelService hotelService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Hotel>> createHotel(@RequestBody HotelDTO hotelDto) {
		return hotelService.createHotel(hotelDto);
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<ResponseStructure<Hotel>> findByHotelId(@PathVariable String hotelId) {
		return hotelService.findByHotelId(hotelId);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Hotel>>>  getAllHotels() {
		return hotelService.getAllHotels();
	}
}
