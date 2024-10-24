package com.praj.hotel.UserService.externalservices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.praj.hotel.UserService.model.Rating;
import com.praj.hotel.UserService.utility.ResponseStructure;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	@GetMapping("/ratings/users/{userId}")
	ResponseStructure<List<Rating>> getRatingsByUserId(@PathVariable("userId") String userId);
}
