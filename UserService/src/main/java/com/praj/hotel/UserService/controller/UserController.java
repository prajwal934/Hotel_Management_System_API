package com.praj.hotel.UserService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.praj.hotel.UserService.dto.UserDto;
import com.praj.hotel.UserService.model.UserEntities;
import com.praj.hotel.UserService.service.UserService;
import com.praj.hotel.UserService.utility.ResponseStructure;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	

    @PostMapping
    public ResponseEntity<ResponseStructure<UserEntities>> saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }
    
    // This method retrieves a user by their userId
    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker" , fallbackMethod = "ratingHotelFallback")

    
//    @RateLimiter(name = "userRateLimiter" , fallbackMethod = "ratingHotelFallback")
  @Retry(name = "retryingHotelRating" , fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<ResponseStructure<UserEntities>> findByUserId(@PathVariable String userId) {
        return userService.findByUserId(userId);
    }
    
//    Creating fall back method forCircuit beaker
    public ResponseEntity<ResponseStructure<UserEntities>> ratingHotelFallback(String userId , Exception ex) {
    	UserEntities user = new UserEntities();
    	user.setUserName("Pagal admi");
    	user.setEmail("pagal@gmail.com");
    	user.setAbout("Hi, I'm here that's why one service maybe down");
    	user.setWorking("Both ( RATING_SERVICE OR HOTEL_SERVICE )"
				+ " OR one of the service is Down. Right now these services are in mantainance...");
    	ResponseStructure<UserEntities> rs = new ResponseStructure<>();
    	rs.setData(user);
    	return new ResponseEntity<>(rs , HttpStatus.OK);
    }
    

    // This method updates a user by their userId
    @PutMapping("/{userId}")
    public ResponseEntity<ResponseStructure<UserEntities>> updateByUserId(@PathVariable String userId, @RequestBody UserDto userDto) {
        return userService.updateByUserId(userId, userDto);
    }
    
    // This method deletes a user by their userId
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseStructure<UserEntities>> deleteByUserId(@PathVariable String userId) {
        return userService.deleteByUserId(userId);
    }
    
    // This method retrieves all users (without userId in the URL)
    @GetMapping
    public ResponseEntity<ResponseStructure<List<UserEntities>>> findAllUsers() {
        return userService.findAllUsers();
    }
 
}
