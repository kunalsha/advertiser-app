package com.coding.exercise.advertiser.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import com.coding.exercise.advertiser.entity.Advertiser;
import com.coding.exercise.advertiser.entity.AdvertiserResponse;
import com.coding.exercise.advertiser.entity.CreditEntry;
import com.coding.exercise.advertiser.entity.ValidatorResponse;
import com.coding.exercise.advertiser.exception.AdvertiserNotFoundException;
import com.coding.exercise.advertiser.exception.InvalidAdvertiserException;
import com.coding.exercise.advertiser.repository.AdvertiserRepository;
import com.coding.exercise.advertiser.service.AdvertiserService;
import com.coding.exercise.advertiser.util.AdvertiserConstants;

import org.junit.Assert;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AdvertiserControllerTest {

	@Mock
	private AdvertiserRepository advRepository;

	@InjectMocks
	private AdvertiserController advertiserController;

	@Mock
	private AdvertiserService advertiserService;

	@Before
	public void dataSetup() {
		MockitoAnnotations.initMocks(this);

		advRepository.save(new Advertiser("Adv-Name1", "Adv-Contact-Name1", 3431.00));
		advRepository.save(new Advertiser("Adv-Name2", "Adv-Contact-Name2", 3534.50));
		advRepository.save(new Advertiser("Adv-Name3", "Adv-Contact-Name3", 7565.10));
		advRepository.save(new Advertiser("Adv-Name4", "Adv-Contact-Name4", 7565.78));
		advRepository.save(new Advertiser("Adv-Name5", "Adv-Contact-Name5", 3432.45));
	}

	@Test
	public void getAdvertiserByIdTestNew() throws Exception {
		Advertiser advertiser = new Advertiser(1L, "Adv-Name1", "Adv-Contact-Name1", 3431.00);

		Mockito.when(advertiserService.getAdvertiserById("1")).thenReturn(advertiser);

		ResponseEntity<AdvertiserResponse> response = advertiserController.getAdvertiserById("1");

		Assert.assertEquals(advertiser.getId(), response.getBody().getAdvertiser().getId());
		Assert.assertEquals(advertiser.getAdvContactName(), response.getBody().getAdvertiser().getAdvContactName());
		Assert.assertEquals(advertiser.getAdvName(), response.getBody().getAdvertiser().getAdvName());
		Assert.assertEquals(advertiser.getAdvCreditLimit(), response.getBody().getAdvertiser().getAdvCreditLimit());
	}

	@Test(expected = InvalidAdvertiserException.class)
	public void getAdvertiserByIdTest_InvalidException() throws Exception {
		Advertiser advertiser = new Advertiser(1L, "Adv-Name1", "Adv-Contact-Name1", 3431.00);

		Mockito.when(advertiserService.getAdvertiserById("1")).thenReturn(advertiser);

		advertiserController.getAdvertiserById("kunal");
	}

	@Test(expected = AdvertiserNotFoundException.class)
	public void getAdvertiserByIdTest_AdvertiserNotFoundException() throws Exception {
		Mockito.when(advertiserService.getAdvertiserById("10"))
				.thenThrow(new AdvertiserNotFoundException(AdvertiserConstants.MSG_NO_ADV));

		advertiserController.getAdvertiserById("10");
	}

	@Test
	public void validateAdvertiserByCreditTest_False() throws Exception {
		Mockito.when(advertiserService.validateAdvertiser("1")).thenReturn(false);

		ResponseEntity<ValidatorResponse> response = advertiserController.validateAdvertiserByCredit("1");

		Assert.assertEquals(false, response.getBody().isCreditToPerformTransactiont());

	}

	@Test
	public void validateAdvertiserByCreditTest_True() throws Exception {
		Mockito.when(advertiserService.validateAdvertiser("3")).thenReturn(true);

		ResponseEntity<ValidatorResponse> response = advertiserController.validateAdvertiserByCredit("3");

		Assert.assertEquals(true, response.getBody().isCreditToPerformTransactiont());

	}

	@Test(expected = InvalidAdvertiserException.class)
	public void validateAdvertiserByCreditTest_Exception() throws Exception {
		Mockito.when(advertiserService.validateAdvertiser("kunal"))
				.thenThrow(new AdvertiserNotFoundException(AdvertiserConstants.MSG_NO_ADV));

		advertiserController.validateAdvertiserByCredit("kunal");
	}

	@Test(expected = InvalidAdvertiserException.class)
	public void createAdvertiserTest_Exception() throws Exception {
		Advertiser advertiser = new Advertiser("", "Adv-Contact-Name1", 3431.00);

		Mockito.when(advertiserService.createAdvertiser(advertiser))
				.thenThrow(new AdvertiserNotFoundException(AdvertiserConstants.MSG_NO_ADV));

		advertiserController.createAdvertiser(advertiser);
	}

	@Test
	public void createAdvertiserTest() throws Exception {
		Advertiser advertiser = new Advertiser("Adv-Name1", "Adv-Contact-Name1", 3431.00);

		Advertiser advertiserResp = new Advertiser(6L, "Adv-Name1", "Adv-Contact-Name1", 3431.00);

		Mockito.when(advertiserService.createAdvertiser(advertiser)).thenReturn(advertiserResp);

		ResponseEntity<AdvertiserResponse> response = advertiserController.createAdvertiser(advertiser);

		Assert.assertEquals(advertiserResp.getId(), response.getBody().getAdvertiser().getId());
	}

	@Test(expected = InvalidAdvertiserException.class)
	public void updateAdvertiserTest() throws Exception {
		Advertiser advertiser = new Advertiser("", "Adv-Contact-Name1", 3431.00);

		Mockito.when(advertiserService.updateAdvertiser(advertiser, "1"))
				.thenThrow(new AdvertiserNotFoundException(AdvertiserConstants.MSG_NO_ADV));

		advertiserController.updateAdvertiser(advertiser, "1");

	}
	
	@Test
	public void deductCreditTest() throws Exception {
		Advertiser advertiserResp = new Advertiser(3L, "Adv-Name3", "Adv-Contact-Name3", 7065.10);

		Mockito.when(advertiserService.deductCredit("3", 500.00)).thenReturn(advertiserResp);
		
		CreditEntry entry = new CreditEntry(500.00);

		ResponseEntity<AdvertiserResponse> response = advertiserController.deductCredit(entry, "3");

		Assert.assertEquals(advertiserResp.getAdvCreditLimit(), response.getBody().getAdvertiser().getAdvCreditLimit());
	}

	@Test(expected = AdvertiserNotFoundException.class)
	public void deductCreditTestException() throws Exception {
		Mockito.when(advertiserService.deductCredit("10", 500.00))
				.thenThrow(new AdvertiserNotFoundException(AdvertiserConstants.MSG_NO_ADV));

		CreditEntry entry = new CreditEntry(500.00);

		advertiserController.deductCredit(entry, "10");
	}

	@Test
	public void updateAdvertiser() throws Exception {
		Advertiser advertiser = new Advertiser("Adv-Name1", "Adv-Contact-Name1", 3431.00);

		Advertiser advertiserResp = new Advertiser(6L, "Adv-Name1", "Adv-Contact-Name1", 3431.00);

		Mockito.when(advertiserService.updateAdvertiser(advertiser, "1")).thenReturn(advertiserResp);

		ResponseEntity<AdvertiserResponse> response = advertiserController.updateAdvertiser(advertiser, "1");

		Assert.assertEquals(advertiserResp.getId(), response.getBody().getAdvertiser().getId());
	}

	@Test
	public void deleteAdvertiser_Test() throws Exception {
		advertiserController.deleteAdvertiser("1");

		ResponseEntity<AdvertiserResponse> response = advertiserController.getAdvertiserById("1");

		Assert.assertNull(response.getBody().getAdvertiser());

	}

}