package com.praj.hotel.UserService.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class UserNotFoundByIdException extends RuntimeException {

	private String message;
}
