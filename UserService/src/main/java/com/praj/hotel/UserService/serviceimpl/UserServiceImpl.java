package com.praj.hotel.UserService.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.praj.hotel.UserService.dto.UserDto;
import com.praj.hotel.UserService.exceptions.UserNotFoundByIdException;
import com.praj.hotel.UserService.externalservices.HotelService;
import com.praj.hotel.UserService.externalservices.RatingService;
import com.praj.hotel.UserService.model.Hotel;
import com.praj.hotel.UserService.model.Rating;
import com.praj.hotel.UserService.model.UserEntities;
import com.praj.hotel.UserService.repository.UserRepository;
import com.praj.hotel.UserService.service.UserService;
import com.praj.hotel.UserService.utility.ResponseStructure;


@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private ResponseStructure<UserEntities> responseStructure;
	private ResponseStructure<List<UserEntities>> rs;
	private RestTemplate restTemplate;
	
	private HotelService hotelService;
	private RatingService ratingService;
	
	private Logger logger =  LoggerFactory.getLogger(UserServiceImpl.class);

	
	//	 This method for saving the user data

	public UserServiceImpl(UserRepository userRepository, ResponseStructure<UserEntities> responseStructure,
			ResponseStructure<List<UserEntities>> rs,
			RestTemplate restTemplate, HotelService hotelService, RatingService ratingService) {
		super();
		this.userRepository = userRepository;
		this.responseStructure = responseStructure;
		this.rs = rs;
		this.restTemplate = restTemplate;
		this.hotelService = hotelService;
		this.ratingService = ratingService;
	}

	@Override
	public ResponseEntity<ResponseStructure<UserEntities>> saveUser(UserDto userDto) {

		return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
				.setMessage("User Data Saved Successfully!")
				.setData(userRepository.save(mapToUser(userDto))));
	}

	private UserEntities mapToUser(UserDto userDto) {
		String randomUserId = UUID.randomUUID().toString(); //for generating unique userId
		//		userEntities.setUserId(randomUserId);
		return UserEntities.builder()
				.userName(userDto.getUserName())
				.email(userDto.getEmail())
				.about(userDto.getAbout())
				.working(userDto.getWorking())
				.userId(randomUserId).build();
	}

	
	@Override
	public ResponseEntity<ResponseStructure<UserEntities>> findByUserId(String userId) {
	    ResponseStructure<UserEntities> responseStructure = new ResponseStructure<>();

	    // Fetch the user from the database using userRepository
	    return userRepository.findById(userId).map(user -> {
	        
	        // Fetch ratings for the user from the Rating Service using FeignClient
	        ResponseStructure<List<Rating>>  ratingsOfUser = ratingService.getRatingsByUserId(user.getUserId());
	        logger.info("Ratings fetched successfully for user {}: {}", userId, ratingsOfUser);
	        
	        // Convert ratings list and fetch hotel details for each rating
	        List<Rating> ratingList = ratingsOfUser.getData().stream()
	            .map(rating -> {
	                // Fetch hotel details for each rating using FeignClient
	                ResponseStructure<Hotel> hotel = hotelService.getHotel(rating.getHotelId());
	                rating.setHotel(hotel.getData());  // Associate the fetched hotel with the rating
	                
	                return rating;
	            })
	            .collect(Collectors.toList());

	        // Set ratings in user entity
	        user.setRatings(ratingList);

	        // Set up the response structure
	        responseStructure.setStatuscode(HttpStatus.OK.value())
	                         .setMessage("User has been found successfully!")
	                         .setData(user);

	        return ResponseEntity.ok(responseStructure);
	    }).orElseThrow(() -> new UserNotFoundByIdException("Sorry, User not found!"));
	}



	//	This method is for Update the data of the User.
	@Override
	public ResponseEntity<ResponseStructure<UserEntities>> updateByUserId(String userId , UserDto userDto) {
		UserEntities updatedUserEntities = mapToUser(userDto);
		return userRepository.findById(userId).map(exUser -> {
			updatedUserEntities.setUserId(exUser.getUserId());
			exUser = userRepository.save(updatedUserEntities);
			return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
					.setMessage("User Data Updated Successfully!")
					.setData(updatedUserEntities));
		}).orElseThrow( () -> new UserNotFoundByIdException("User Not found"));

	}

	//	This method to delete a particular user.

	@Override
	public ResponseEntity<ResponseStructure<UserEntities>> deleteByUserId(String userId) {
		Optional<UserEntities> optional = userRepository.findById(userId);
		return optional.map(user -> {userRepository.delete(user);
		return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
				.setMessage("User Data Deleted Successfully!"));
		}).orElseThrow(null);
	}

	//	To get all the Users data.
	@Override
	public ResponseEntity<ResponseStructure<List<UserEntities>>> findAllUsers() {
		return ResponseEntity.ok(rs.setStatuscode(HttpStatus.OK.value())
				.setMessage("User Data fetched Successfully!")
				.setData(userRepository.findAll()));
	}

	
	


	

}
