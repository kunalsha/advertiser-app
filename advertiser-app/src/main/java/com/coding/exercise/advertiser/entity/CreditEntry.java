package com.coding.exercise.advertiser.entity;

/**
 * CreditEntry is the DTO class
 * Created by - Kunal Sharma
 */
public class CreditEntry {

	private double advCreditAmount;

	public CreditEntry() {
		super();
	}

	public CreditEntry(double advCreditAmount) {
		super();
		this.advCreditAmount = advCreditAmount;
	}

	public double getAdvCreditAmount() {
		return advCreditAmount;
	}

	public void setAdvCreditAmount(double advCreditAmount) {
		this.advCreditAmount = advCreditAmount;
	}
}
