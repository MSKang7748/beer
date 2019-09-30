package com.springbeer.beerproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.springbeer.beerproject.entity.CartEntity;

public interface CartRepository extends CrudRepository<CartEntity, Integer> {

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM CartEntity as cart " + 
			   	   "WHERE cart.beerNo = :beerNo")
	void deleteById(@Param(value = "beerNo") Long beerNo);
	

//	List<CartEntity> findByBeerNo(Long beerNo);
	

	
}
