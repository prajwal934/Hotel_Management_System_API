package com.praj.hotel.HotelService.serviceimpl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.praj.hotel.HotelService.dto.HotelDTO;
import com.praj.hotel.HotelService.exceptions.HotelNotFoundByIdException;
import com.praj.hotel.HotelService.model.Hotel;
import com.praj.hotel.HotelService.repository.HotelRepository;
import com.praj.hotel.HotelService.service.HotelService;
import com.praj.hotel.HotelService.utility.ResponseStructure;

@Service
public class HotelServiceImpl implements HotelService{

	private HotelRepository hotelRepository;
	private ResponseStructure<Hotel> responseStructure;
	private ResponseStructure<List<Hotel>> rs;
	
	
	public HotelServiceImpl(HotelRepository hotelRepository, ResponseStructure<Hotel> responseStructure,
			ResponseStructure<List<Hotel>> rs) {
		super();
		this.hotelRepository = hotelRepository;
		this.responseStructure = responseStructure;
		this.rs = rs;
	}

	@Override
	public ResponseEntity<ResponseStructure<Hotel>> createHotel(HotelDTO hotelDto) {
		return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
				.setMessage("Hotel Created Successfully")
				.setData(hotelRepository.save(mapToHotel(hotelDto))));
	}


	private Hotel mapToHotel(HotelDTO hotelDto) {
		String randomHotelId = UUID.randomUUID().toString(); //for generating unique hotelId;
	return Hotel.builder()
			.hotelName(hotelDto.getHotelName())
			.location(hotelDto.getLocation())
			.about(hotelDto.getAbout())
			.hotelId(randomHotelId).build();
}

	@Override
	public ResponseEntity<ResponseStructure<List<Hotel>>> getAllHotels() {
		return ResponseEntity.ok(rs.setStatuscode(HttpStatus.OK.value())
				.setMessage("Hotel Data fetched Successfully!")
				.setData(hotelRepository.findAll()));
		
	}


	@Override
	public ResponseEntity<ResponseStructure<Hotel>> findByHotelId(String hotelId) {
		return hotelRepository.findById(hotelId).map(hotel -> ResponseEntity.ok(
				responseStructure.setStatuscode(HttpStatus.OK.value())
				.setMessage("Hotel has been founded successfully!").setData(hotel)))
				.orElseThrow( () -> new HotelNotFoundByIdException("Sorry, User not found!"));
	}
	
	
	
}
