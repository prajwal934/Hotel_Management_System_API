package com.praj.hotel.HotelService.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class HotelNotFoundByIdException extends  RuntimeException{

	private String message;
}
