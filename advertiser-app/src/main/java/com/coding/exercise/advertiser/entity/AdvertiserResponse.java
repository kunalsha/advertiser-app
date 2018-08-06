package com.coding.exercise.advertiser.entity;

import com.coding.exercise.advertiser.exception.ErrorResponse;

/**
 * AdvertiserResponse is the DTO class
 * Created by Kunal Sharma
 */
public class AdvertiserResponse {

	private Advertiser advertiser;
	private ErrorResponse errorResponse;

	public AdvertiserResponse(Advertiser advertiser, ErrorResponse errorResponse) {
		super();
		this.advertiser = advertiser;
		this.errorResponse = errorResponse;
	}
	
	public Advertiser getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(Advertiser advertiser) {
		this.advertiser = advertiser;
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

	@Override
	public String toString() {
		return "AdvertiserResponse [advertiser=" + advertiser + ", errorResponse=" + errorResponse + "]";
	}
}
