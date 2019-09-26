package com.springbeer.beerproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springbeer.beerproject.entity.CartEntity;

public interface CartRepository extends CrudRepository<CartEntity, Integer> {
	
	List<CartEntity> findById(Long beerNo);

//	List<CartEntity> findByBeerNo(Long beerNo);
	

	
}
