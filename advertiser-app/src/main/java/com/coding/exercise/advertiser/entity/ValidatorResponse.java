package com.coding.exercise.advertiser.entity;

/**
 * ValidatorResponse is the DTO class
 * Created by Kunal Sharma
 */
public class ValidatorResponse {

	private boolean validCreditLimit;

	public ValidatorResponse(boolean validCreditLimit) {
		super();
		this.validCreditLimit = validCreditLimit;
	}

	public boolean isValidCreditLimit() {
		return validCreditLimit;
	}

	public void setValidCreditLimit(boolean validCreditLimit) {
		this.validCreditLimit = validCreditLimit;
	}

	@Override
	public String toString() {
		return "AdvertiserResponse [validCreditLimit=" + validCreditLimit + "]";
	}
}
