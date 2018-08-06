package com.coding.exercise.advertiser.exception;

import org.springframework.http.HttpStatus;

/**
 * ErrorResponse is a custom generic class created to hold error code and message. 
 * Created by Kunal Sharma
 */
public class ErrorResponse {

	private HttpStatus errorCode;

	private  String errorMessage;

	public ErrorResponse(HttpStatus errorCode, String errorMessage) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}
}
