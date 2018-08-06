package com.coding.exercise.advertiser.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * AdvertiseErrorAdvice is Spring AOP Error Handler class Created by Kunal
 * Sharma
 */
@ControllerAdvice
class AdvertiseErrorAdvice {

	@ResponseBody
	@ExceptionHandler(AdvertiserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ErrorResponse AdvertiserNotFoundExceptionHandler(AdvertiserNotFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(InvalidAdvertiserException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ErrorResponse InvalidAdvertiserExceptionHandler(InvalidAdvertiserException ex) {
		return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(AdvertiserDBException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	ErrorResponse AdvertiserDBExceptionHandler(AdvertiserDBException ex) {
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	}
}
