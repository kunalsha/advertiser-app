package com.coding.exercise.advertiser.controller;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.exercise.advertiser.entity.Advertiser;
import com.coding.exercise.advertiser.entity.AdvertiserResponse;
import com.coding.exercise.advertiser.entity.CreditEntry;
import com.coding.exercise.advertiser.entity.ValidatorResponse;
import com.coding.exercise.advertiser.exception.InvalidAdvertiserException;
import com.coding.exercise.advertiser.service.AdvertiserService;
import com.coding.exercise.advertiser.util.AdvertiserConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/advertiser")
@Api(value = "Advertiser", description = "Endpoint for Advertiser management")
public class AdvertiserController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdvertiserController.class);

	@Autowired
	private AdvertiserService advertiserService;

	// POST new advertiser
	@ApiOperation(value = "Create new Advertiser", notes = "Creates a new Advertiser from Advertiser object information passed", response = ResponseEntity.class)
	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiserResponse> createAdvertiser(@RequestBody Advertiser advertiser) {

		logger.info("Inside AdvertiserController.createAdvertiser, value of advertiserId is " + advertiser.getId());

		if (!validateAdvertiser(advertiser))
			throw new InvalidAdvertiserException(AdvertiserConstants.MSG_INVL_INPUT);

		logger.info("Exiting AdvertiserController.createAdvertiser ");

		return new ResponseEntity<AdvertiserResponse>(
				new AdvertiserResponse(advertiserService.createAdvertiser(advertiser), null), HttpStatus.OK);
	}

	// POST deduct credit from the advertiser’s account
	@ApiOperation(value = "Deduct credit from the advertiser’s account", notes = "Deduct credit from the advertiser’s account", response = ResponseEntity.class)
	@PostMapping(value = "/{advertiserId}/deduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiserResponse> deductCredit(@RequestBody CreditEntry creditEntry,
			@PathVariable(name = "advertiserId", required = true) String advertiserId) {

		logger.info("Inside AdvertiserController.deductCredit, value of advertiserId is "
				+ creditEntry.getAdvCreditAmount());

		return new ResponseEntity<AdvertiserResponse>(new AdvertiserResponse(
				advertiserService.deductCredit(advertiserId, creditEntry.getAdvCreditAmount()), null), HttpStatus.OK);
	}

	// PUT update advertiser
	@ApiOperation(value = "Update an existing Advertiser", notes = "Update an existing Advertiser from Advertiser object information passed", response = ResponseEntity.class)
	@PutMapping(value = "/{advertiserId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiserResponse> updateAdvertiser(@Valid @RequestBody Advertiser advertiser,
			@PathVariable(name = "advertiserId", required = true) String advertiserId) {

		logger.info("Inside AdvertiserController.updateAdvertiser, value of advertiser is invalid ");

		if (advertiser.getAdvName() == null || advertiser.getAdvName().trim().equals("")
				|| advertiser.getAdvContactName() == null || advertiser.getAdvContactName().trim().equals("")
				|| advertiser.getAdvCreditLimit() == null || !(StringUtils.isNumeric(advertiserId)))
			throw new InvalidAdvertiserException(AdvertiserConstants.MSG_INVL_INPUT);

		logger.info("Exiting AdvertiserController.updateAdvertiser");

		return new ResponseEntity<AdvertiserResponse>(
				new AdvertiserResponse(advertiserService.updateAdvertiser(advertiser, advertiserId), null),
				HttpStatus.OK);
	}

	// DELETE advertiser
	@ApiOperation(value = "Delete an existing Advertiser", notes = "Delete an existing Advertiser based on advertiser Id", response = ResponseEntity.class)
	@DeleteMapping(value = "/{advertiserId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteAdvertiser(
			@Valid @PathVariable(name = "advertiserId", required = true) String advertiserId) {

		logger.info("Inside AdvertiserController.deleteAdvertiser, value of advertiserId is " + advertiserId);

		if (advertiserId == null || advertiserId.trim().equals("") || !(StringUtils.isNumeric(advertiserId)))
			throw new InvalidAdvertiserException(
					AdvertiserConstants.MSG_INVL_INPUT + advertiserId + AdvertiserConstants.MSG_INVL_INPUT1);

		advertiserService.deleteAdvertiser(advertiserId);

		logger.info("Exiting AdvertiserController.deleteAdvertiser");

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	// GET advertiser
	@ApiOperation(value = "Returns an existing Advertiser", notes = "Returns an existing Advertiser based on advertiser Id", response = ResponseEntity.class)
	@GetMapping(value = "/{advertiserId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiserResponse> getAdvertiserById(
			@Valid @PathVariable(name = "advertiserId", required = true) String advertiserId) {

		logger.info("Inside AdvertiserController.getAdvertiserById, value of advertiserId is " + advertiserId);

		if (advertiserId == null || advertiserId.trim().equals("") || !(StringUtils.isNumeric(advertiserId)))
			throw new InvalidAdvertiserException(
					AdvertiserConstants.MSG_INVL_INPUT + advertiserId + AdvertiserConstants.MSG_INVL_INPUT1);

		logger.info("Exiting AdvertiserController.getAdvertiserById");

		return new ResponseEntity<AdvertiserResponse>(
				new AdvertiserResponse(advertiserService.getAdvertiserById(advertiserId), null), HttpStatus.OK);
	}

	// GET endpoint to validate if the advertiser has enough credit to perform a
	// transaction
	@ApiOperation(value = "Returns status of credit to perform a transaction", notes = "Returns status of credit to perform a transaction based on credit limit of advertiser Id", response = ResponseEntity.class)
	@GetMapping(value = "/{advertiserId}/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ValidatorResponse> validateAdvertiserByCredit(
			@PathVariable(name = "advertiserId", required = true) String advertiserId) {

		logger.info("Inside AdvertiserController.validateAdvertiserByCredit, value of advertiserId is " + advertiserId);

		if (advertiserId == null || advertiserId.trim().equals("") || !(StringUtils.isNumeric(advertiserId)))
			throw new InvalidAdvertiserException(
					AdvertiserConstants.MSG_INVL_INPUT + advertiserId + AdvertiserConstants.MSG_INVL_INPUT1);

		logger.info("Exiting AdvertiserController.validateAdvertiserByCredit");

		return new ResponseEntity<ValidatorResponse>(
				new ValidatorResponse(advertiserService.validateAdvertiser(advertiserId)), HttpStatus.OK);
	}

	public boolean validateAdvertiser(Advertiser advertiser) {

		logger.info("Inside AdvertiserController.validateAdvertiser");

		if (advertiser.getAdvName() == null || advertiser.getAdvName().trim().equals("")
				|| advertiser.getAdvContactName() == null || advertiser.getAdvContactName().trim().equals("")
				|| advertiser.getAdvCreditLimit() == null)
			return false;
		else
			return true;
	}

}
