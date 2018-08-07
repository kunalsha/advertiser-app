package com.coding.exercise.advertiser.service;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.exercise.advertiser.controller.AdvertiserController;
import com.coding.exercise.advertiser.entity.Advertiser;
import com.coding.exercise.advertiser.exception.*;
import com.coding.exercise.advertiser.repository.AdvertiserRepository;
import com.coding.exercise.advertiser.util.AdvertiserConstants;

/**
 * AdvertiserService class handles Service layer operations Created by Kunal
 * Sharma
 */
@Service
public class AdvertiserService {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdvertiserController.class);

	@Autowired
	private AdvertiserRepository repo;

	public Advertiser getAdvertiserById(String advertiserId) {

		logger.info("Inside AdvertiserRepository.getAdvertiserById, value of advertiserId is " + advertiserId);

		return repo.findById(Long.parseLong(advertiserId))
				.orElseThrow(() -> new AdvertiserNotFoundException(AdvertiserConstants.MSG_NO_ADV));
	}

	public boolean validateAdvertiser(String advertiserId) {

		logger.info("Inside AdvertiserRepository.validateAdvertiser, value of advertiserId is " + advertiserId);

		Advertiser advertiser = repo.findById(Long.parseLong(advertiserId))
				.orElseThrow(() -> new AdvertiserNotFoundException(AdvertiserConstants.MSG_NO_ADV));

		logger.info("Exiting AdvertiserRepository.validateAdvertiser");

		return advertiser.getAdvCreditLimit() > AdvertiserConstants.CREDIT_LIMIT ? true : false;
	}

	public Advertiser createAdvertiser(Advertiser advertiser) {

		logger.info("Inside AdvertiserRepository.createAdvertiser");

		Advertiser newAdv = repo.save(advertiser);

		if (newAdv == null)
			throw new AdvertiserDBException(AdvertiserConstants.MSG_SAVE_FAILED);

		logger.info("Exiting AdvertiserRepository.createAdvertiser , value of advertiserId is " + newAdv.getId());

		return newAdv;
	}

	public Advertiser updateAdvertiser(Advertiser advertiser, String advertiserId) {

		logger.info("Inside AdvertiserRepository.updateAdvertiser, value of advertiserId is " + advertiserId);

		Advertiser newAdv = repo.findById(Long.parseLong(advertiserId))
				.orElseThrow(() -> new AdvertiserNotFoundException(AdvertiserConstants.MSG_NO_ADV));

		if (newAdv == null)
			throw new AdvertiserNotFoundException(AdvertiserConstants.MSG_NO_ADV);

		newAdv.setAdvContactName(advertiser.getAdvContactName());
		newAdv.setAdvName(advertiser.getAdvName());
		newAdv.setAdvCreditLimit(advertiser.getAdvCreditLimit());

		Advertiser result = repo.save(newAdv);

		if (result == null)
			throw new AdvertiserDBException(AdvertiserConstants.MSG_UPDT_FAILED);

		logger.info("Exiting AdvertiserRepository.updateAdvertiser");

		return result;
	}

	public void deleteAdvertiser(@Valid String advertiserId) {

		logger.info("Inside AdvertiserRepository.deleteAdvertiser, value of advertiserId is " + advertiserId);

		repo.deleteById(Long.parseLong(advertiserId));
	}

	public Advertiser deductCredit(String advertiserId, double advCreditEntry) {
		logger.info("Inside AdvertiserRepository.deductCredit, value of advertiserId is " + advertiserId);

		Advertiser newAdv = repo.findById(Long.parseLong(advertiserId))
				.orElseThrow(() -> new AdvertiserNotFoundException(AdvertiserConstants.MSG_NO_ADV));

		if (newAdv == null)
			throw new AdvertiserNotFoundException(AdvertiserConstants.MSG_NO_ADV);

		double newCredit = newAdv.getAdvCreditLimit() - advCreditEntry;
		newAdv.setAdvCreditLimit(newCredit);

		Advertiser result = repo.save(newAdv);

		if (result == null)
			throw new AdvertiserDBException(AdvertiserConstants.MSG_UPDT_FAILED);

		logger.info("Exiting AdvertiserRepository.deductCredit");

		return result;
	}
}
