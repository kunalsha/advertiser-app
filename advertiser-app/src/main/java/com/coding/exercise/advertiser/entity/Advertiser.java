package com.coding.exercise.advertiser.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.NumberFormat;

/**
 * Advertiser is the Entity class corresponding to Advertiser table Created by
 * Kunal Sharma
 */
@Entity
@Table(name = "Advertiser")
public class Advertiser {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@NumberFormat
	private Long Id;

	@NotBlank
	@Column(name = "advertiser_name")
	private String advName;

	@Column(name = "advertiser_contact_name")
	private String advContactName;

	@Positive
	@Column(name = "advertiser_credit_limit")
	private Double advCreditLimit;

	public Advertiser() {
		super();
	}

	public Advertiser(String advName, String advContactName, Double advCreditLimit) {
		super();
		this.advName = advName;
		this.advContactName = advContactName;
		this.advCreditLimit = advCreditLimit;
	}

	public Advertiser(Long id, @NotBlank String advName, String advContactName, @Positive Double advCreditLimit) {
		super();
		Id = id;
		this.advName = advName;
		this.advContactName = advContactName;
		this.advCreditLimit = advCreditLimit;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getAdvName() {
		return advName;
	}

	public void setAdvName(String advName) {
		this.advName = advName;
	}

	public String getAdvContactName() {
		return advContactName;
	}

	public void setAdvContactName(String advContactName) {
		this.advContactName = advContactName;
	}

	public Double getAdvCreditLimit() {
		return advCreditLimit;
	}

	public void setAdvCreditLimit(Double advCreditLimit) {
		this.advCreditLimit = advCreditLimit;
	}
}
