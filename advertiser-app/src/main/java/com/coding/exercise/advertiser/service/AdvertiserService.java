package com.coding.exercise.advertiser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Autowired
	private AdvertiserRepository repo;

	public Advertiser getAdvertiserById(String advertiserId) {

		return repo.findById(Long.parseLong(advertiserId))
				.orElseThrow(() -> new AdvertiserNotFoundException(AdvertiserConstants.MSG_NO_ADV));
	}

	public boolean validateAdvertiser(String advertiserId) {

		Advertiser advertiser = repo.findById(Long.parseLong(advertiserId))
				.orElseThrow(() -> new AdvertiserNotFoundException(AdvertiserConstants.MSG_NO_ADV));

		return advertiser.getAdvCreditLimit() > AdvertiserConstants.CREDIT_LIMIT ? true : false;
	}

	public Advertiser createAdvertiser(Advertiser advertiser) {

		Advertiser newAdv = repo.save(advertiser);

		if (newAdv == null)
			throw new AdvertiserDBException(AdvertiserConstants.MSG_SAVE_FAILED);

		return newAdv;
	}

	public Advertiser updateAdvertiser(Advertiser advertiser, String advertiserId) {

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

		return result;
	}

}
