package com.coding.exercise.advertiser.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import com.coding.exercise.advertiser.entity.Advertiser;
import com.coding.exercise.advertiser.exception.AdvertiserNotFoundException;
import com.coding.exercise.advertiser.repository.AdvertiserRepository;

@RunWith(SpringRunner.class)
public class AdvertiserServiceTest {

	@Mock
	private AdvertiserRepository repo;

	@InjectMocks
	private AdvertiserService advertiserService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		repo.save(new Advertiser("Adv-Name1", "Adv-Contact-Name1", 3431.00));
		repo.save(new Advertiser("Adv-Name2", "Adv-Contact-Name2", 3534.50));
		repo.save(new Advertiser("Adv-Name3", "Adv-Contact-Name3", 7565.10));
		repo.save(new Advertiser("Adv-Name4", "Adv-Contact-Name4", 7565.78));
		repo.save(new Advertiser("Adv-Name5", "Adv-Contact-Name5", 3432.45));

	}

	@Test
	public void getAdvertiserByIdTest() {
		Optional<Advertiser> adv = Optional.of(new Advertiser("Adv-Name1", "Adv-Contact-Name1", 3431.00));

		when(repo.findById(1L)).thenReturn(adv);

		Advertiser response = advertiserService.getAdvertiserById("1");
		assertEquals(adv.get(), response);
	}

	@Test
	public void validateAdvertiserTest() {
		Optional<Advertiser> advFalse = Optional.of(new Advertiser("Adv-Name1", "Adv-Contact-Name1", 3431.00));
		Optional<Advertiser> advTrue = Optional.of(new Advertiser("Adv-Name3", "Adv-Contact-Name3", 7565.10));

		when(repo.findById(1L)).thenReturn(advFalse);

		boolean responseFalse = advertiserService.validateAdvertiser("1");
		assertEquals(false, responseFalse);

		when(repo.findById(3L)).thenReturn(advTrue);

		boolean responseTrue = advertiserService.validateAdvertiser("3");
		assertEquals(true, responseTrue);

	}

	@Test
	public void createAdvertiserTest() {

		Advertiser advertiserReq = new Advertiser("Adv-Name6", "Adv-Contact-Name6", 7565.10);

		Advertiser advertiserResp = new Advertiser(6L, "Adv-Name6", "Adv-Contact-Name6", 7565.10);

		when(repo.save(advertiserReq)).thenReturn(advertiserResp);

		Advertiser response = advertiserService.createAdvertiser(advertiserReq);

		assertEquals(advertiserResp.getAdvContactName(), response.getAdvContactName());
		assertEquals(advertiserResp.getAdvName(), response.getAdvName());
		assertEquals(advertiserResp.getAdvCreditLimit(), response.getAdvCreditLimit());

	}

	@Test
	public void updateAdvertiserTest() {

		Advertiser advertiser = new Advertiser(3L, "Adv-Name31", "Adv-Contact-Name31", 75651.10);

		Optional<Advertiser> adv = Optional.of(new Advertiser(3L, "Adv-Name3", "Adv-Contact-Name3", 7565.10));

		when(repo.findById(3L)).thenReturn(adv);

		when(repo.save(Mockito.any(Advertiser.class))).thenReturn(advertiser);

		Advertiser response = advertiserService.updateAdvertiser(advertiser, "3");

		assertEquals(advertiser.getAdvContactName(), response.getAdvContactName());
		assertEquals(advertiser.getAdvName(), response.getAdvName());
		assertEquals(advertiser.getAdvCreditLimit(), response.getAdvCreditLimit());
	}

	@Test(expected = AdvertiserNotFoundException.class)
	public void deleteAdvertiserByIdTest() {

		advertiserService.deleteAdvertiser("1");

		advertiserService.getAdvertiserById("1");
	}
}
