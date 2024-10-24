package com.praj.hotel.UserService.payload;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.praj.hotel.UserService.exceptions.UserNotFoundByIdException;
import com.praj.hotel.UserService.utility.ErrorStructure;

import lombok.AllArgsConstructor;


@RestControllerAdvice
@AllArgsConstructor
public class ApplicationExceptionHandler  extends ResponseEntityExceptionHandler{
	private ErrorStructure errorStructure;

	private ResponseEntity<ErrorStructure> errorResponse(HttpStatus status , String errorMessage , Object rootCause) {
		return new ResponseEntity<ErrorStructure> (errorStructure.setStatuscode(status.value())
				.setErrorMessage(errorMessage)
				.setRootCause(rootCause), status);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers , HttpStatusCode statusCode , WebRequest request) {
		Map<String , String > messages = new HashMap<>();
		
		ex.getAllErrors().forEach(error -> {
			FieldError fe = (FieldError) error;
			messages.put(fe.getField(), error.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errorStructure.setErrorMessage("invalid inputs")
				.setStatuscode(HttpStatus.BAD_REQUEST.value()).setRootCause(messages));
	}	
	
	@ExceptionHandler
	private ResponseEntity<ErrorStructure> handelUserAlreadyExistByEmail(UserNotFoundByIdException ex) {
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "User Already Exist with the given  Id");
	}
	
	
	
}
