package com.springbeer.beerproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.springbeer.beerproject.entity.CartEntity;
import com.springbeer.beerproject.entity.ProductEntity;

public interface CartRepository extends CrudRepository<CartEntity, Integer> {

//	@Modifying
//	@Transactional
//	@Query(value = "DELETE FROM CartEntity as cart " + 
//			   	   "WHERE cart.memberNo = :memberNo")
//	void deleteById(@Param(value = "memberNo") int memberNo);
	

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM CartEntity as cart " + 
			   	   "WHERE cart.cartId = :cartId")
	void deleteById(@Param(value = "cartId") int cartId);

	@Query(value = "SELECT cart " + 
			   "FROM CartEntity as cart " + 
			   "WHERE cart.memberNo = :memberNo", nativeQuery = false)
	List<CartEntity> findByMemberNo(@Param("memberNo")int memberNo);

	

//	List<CartEntity> findByBeerNo(Long beerNo);
	

	
}
