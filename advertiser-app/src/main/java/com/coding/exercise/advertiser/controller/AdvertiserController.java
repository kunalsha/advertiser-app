package com.coding.exercise.advertiser.controller;

import javax.validation.Valid;

import org.h2.util.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.exercise.advertiser.entity.Advertiser;
import com.coding.exercise.advertiser.entity.AdvertiserResponse;
import com.coding.exercise.advertiser.entity.ValidatorResponse;
import com.coding.exercise.advertiser.exception.InvalidAdvertiserException;
import com.coding.exercise.advertiser.service.AdvertiserService;
import com.coding.exercise.advertiser.util.AdvertiserConstants;

@RestController
@RequestMapping("/api/advertiser")
public class AdvertiserController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdvertiserController.class);

	@Autowired
	private AdvertiserService advertiserService;

	@GetMapping(value = "/{advertiserId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiserResponse> getAdvertiserById(
			@Valid @PathVariable(name = "advertiserId", required = true) String advertiserId) {

		logger.info("Inside AdvertiserController.getAdvertiserById, value of advertiserId is " + advertiserId);

		if (advertiserId == null || advertiserId.trim().equals("") || !(StringUtils.isNumber(advertiserId)))
			throw new InvalidAdvertiserException(
					AdvertiserConstants.MSG_INVL_INPUT + advertiserId + AdvertiserConstants.MSG_INVL_INPUT1);

		logger.info("Exiting AdvertiserController.getAdvertiserById");

		return new ResponseEntity<AdvertiserResponse>(
				new AdvertiserResponse(advertiserService.getAdvertiserById(advertiserId), null), HttpStatus.OK);
	}

	@GetMapping(value = "/{advertiserId}/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ValidatorResponse> validateAdvertiserByCredit(
			@PathVariable(name = "advertiserId", required = true) String advertiserId) {

		if (advertiserId == null || advertiserId.trim().equals("") || !(StringUtils.isNumber(advertiserId)))
			throw new InvalidAdvertiserException(
					AdvertiserConstants.MSG_INVL_INPUT + advertiserId + AdvertiserConstants.MSG_INVL_INPUT1);

		return new ResponseEntity<ValidatorResponse>(
				new ValidatorResponse(advertiserService.validateAdvertiser(advertiserId)), HttpStatus.OK);
	}

	@PutMapping(value = "/{advertiserId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiserResponse> updateAdvertiser(@Valid @RequestBody Advertiser advertiser,
			@PathVariable(name = "advertiserId", required = true) String advertiserId) {

		if (advertiser.getAdvName() == null || advertiser.getAdvName().trim().equals("")
				|| advertiser.getAdvContactName() == null || advertiser.getAdvContactName().trim().equals("")
				|| advertiser.getAdvCreditLimit() == null || !(StringUtils.isNumber(advertiserId)))
			throw new InvalidAdvertiserException(AdvertiserConstants.MSG_INVL_INPUT);

		return new ResponseEntity<AdvertiserResponse>(
				new AdvertiserResponse(advertiserService.updateAdvertiser(advertiser, advertiserId), null),
				HttpStatus.OK);
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiserResponse> createAdvertiser(@RequestBody Advertiser advertiser) {

		if (!validateAdvertiser(advertiser))
			throw new InvalidAdvertiserException(AdvertiserConstants.MSG_INVL_INPUT);

		return new ResponseEntity<AdvertiserResponse>(
				new AdvertiserResponse(advertiserService.createAdvertiser(advertiser), null), HttpStatus.OK);
	}

	public boolean validateAdvertiser(Advertiser advertiser) {
		if (advertiser.getAdvName() == null || advertiser.getAdvName().trim().equals("")
				|| advertiser.getAdvContactName() == null || advertiser.getAdvContactName().trim().equals("")
				|| advertiser.getAdvCreditLimit() == null)
			return false;
		else
			return true;
	}

}
