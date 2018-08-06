package com.coding.exercise.advertiser.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAdvertiserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidAdvertiserException(String message) {
		super(message);
	}
}
