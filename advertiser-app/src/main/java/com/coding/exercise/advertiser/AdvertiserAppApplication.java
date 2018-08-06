package com.coding.exercise.advertiser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.coding.exercise.advertiser.entity.Advertiser;
import com.coding.exercise.advertiser.repository.AdvertiserRepository;

@SpringBootApplication
public class AdvertiserAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvertiserAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataSetup(AdvertiserRepository advRepository) {
		return (args) -> {
			advRepository.save(new Advertiser("Adv-Name1", "Adv-Contact-Name1", 3431.00));
			advRepository.save(new Advertiser("Adv-Name2", "Adv-Contact-Name2", 3534.50));
			advRepository.save(new Advertiser("Adv-Name3", "Adv-Contact-Name3", 7565.10));
			advRepository.save(new Advertiser("Adv-Name4", "Adv-Contact-Name4", 7565.78));
			advRepository.save(new Advertiser("Adv-Name5", "Adv-Contact-Name5", 3432.45));
		};
	}
}
