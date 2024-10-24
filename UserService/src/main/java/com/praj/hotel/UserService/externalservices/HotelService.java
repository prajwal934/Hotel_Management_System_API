package com.praj.hotel.UserService.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.praj.hotel.UserService.model.Hotel;
import com.praj.hotel.UserService.utility.ResponseStructure;

@FeignClient(name = "HOTELSERVICE")
public interface HotelService {
	
	@GetMapping("/hotels/{hotelId}")
	ResponseStructure<Hotel> getHotel(@PathVariable("hotelId") String hotelId);

}
