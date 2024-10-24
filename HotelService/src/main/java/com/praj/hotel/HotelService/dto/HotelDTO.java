package com.praj.hotel.HotelService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {

	private String hotelId;
	private String hotelName;
	private String location;
	private String about;
}
