package com.coding.exercise.advertiser.entity;

/**
 * ValidatorResponse is the DTO class Created by Kunal Sharma
 */
public class ValidatorResponse {

	private boolean creditToPerformTransaction;

	public ValidatorResponse(boolean creditToPerformTransaction) {
		super();
		this.creditToPerformTransaction = creditToPerformTransaction;
	}

	public boolean isCreditToPerformTransactiont() {

		return creditToPerformTransaction;
	}

	public void setCreditToPerformTransaction(boolean creditToPerformTransaction) {
		this.creditToPerformTransaction = creditToPerformTransaction;
	}
}
