package com.coding.exercise.advertiser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * AdvertiserDBException is a Custom Exception class to hold any DB Exception
 * Created by Kunal Sharma
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AdvertiserDBException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AdvertiserDBException(String message) {
		super(message);
	}
}
