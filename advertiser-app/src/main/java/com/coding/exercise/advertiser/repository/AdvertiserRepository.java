package com.coding.exercise.advertiser.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coding.exercise.advertiser.entity.Advertiser;

/**
 * AdvertiserRepository is JPA Repository interface for Advertiser object
 * Created by Kunal Sharma
 */
@Repository
@SuppressWarnings("unchecked")
public interface AdvertiserRepository extends CrudRepository<Advertiser, Long> {

	public Optional<Advertiser> findById(Long id);

	public Advertiser save(Advertiser advertiser);

	public void deleteById(Long Id);

}
