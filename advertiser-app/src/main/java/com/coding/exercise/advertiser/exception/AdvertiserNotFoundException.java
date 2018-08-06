package com.coding.exercise.advertiser.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * AdvertiserNotFoundException is a Custom Exception class to handle Advertiser
 * record not found Created by Kunal Sharma
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdvertiserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AdvertiserNotFoundException(String message) {
		super(message);
	}
}
