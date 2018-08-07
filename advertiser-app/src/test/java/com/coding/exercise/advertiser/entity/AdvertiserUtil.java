package com.coding.exercise.advertiser.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import com.coding.exercise.advertiser.entity.Advertiser;
import com.coding.exercise.advertiser.entity.AdvertiserResponse;
import com.coding.exercise.advertiser.entity.ValidatorResponse;
import com.coding.exercise.advertiser.exception.ErrorResponse;

@RunWith(SpringRunner.class)
public class AdvertiserUtil {

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void ErrorResponse_Test() {
		ErrorResponse resp = new ErrorResponse(HttpStatus.ACCEPTED, "ErrorMessage");

		Assert.assertEquals(HttpStatus.ACCEPTED, resp.getErrorCode());
		Assert.assertEquals("ErrorMessage", resp.getErrorMessage());
	}

	@Test
	public void AdvertiserResponse_Test() {

		ErrorResponse errroResp = new ErrorResponse(HttpStatus.ACCEPTED, "ErrorMessage");

		AdvertiserResponse resp = new AdvertiserResponse(new Advertiser("Adv-Name1", "Adv-Contact-Name1", 3431.00),
				errroResp);

		Assert.assertEquals("Adv-Contact-Name1", resp.getAdvertiser().getAdvContactName());
		Assert.assertEquals("Adv-Name1", resp.getAdvertiser().getAdvName());

		resp.setAdvertiser(new Advertiser("Adv-Name2", "Adv-Contact-Name2", 3431.00));

		Assert.assertEquals("Adv-Contact-Name2", resp.getAdvertiser().getAdvContactName());
		Assert.assertEquals("Adv-Name2", resp.getAdvertiser().getAdvName());
	}

	@Test
	public void ValidatorResponse_Test() {
		ValidatorResponse resp = new ValidatorResponse(false);

		Assert.assertEquals(false, resp.isCreditToPerformTransactiont());

		resp.setCreditToPerformTransaction(true);
		Assert.assertEquals(true, resp.isCreditToPerformTransactiont());

	}
	
	@Test
	public void CreditEntry_Test() {
		CreditEntry resp = new CreditEntry();

		resp.setAdvCreditAmount(500.00);
		Assert.assertEquals(500.00, resp.getAdvCreditAmount(),0.00);

	}
}
