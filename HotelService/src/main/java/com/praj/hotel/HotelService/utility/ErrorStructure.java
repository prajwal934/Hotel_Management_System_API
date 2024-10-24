package com.praj.hotel.HotelService.utility;

import org.springframework.stereotype.Component;


@Component
public class ErrorStructure {

	private int statuscode;
	private String errorMessage;
	private Object rootCause;
	public int getStatuscode() {
		return statuscode;
	}
	public ErrorStructure setStatuscode(int statuscode) {
		this.statuscode = statuscode;
		return this;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public ErrorStructure setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}
	public Object getRootCause() {
		return rootCause;
	}
	public ErrorStructure setRootCause(Object rootCause) {
		this.rootCause = rootCause;
		return this;
	}
}
