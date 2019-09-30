package com.springbeer.beerproject.service;

import java.util.List;

import com.springbeer.beerproject.entity.CartEntity;
import com.springbeer.beerproject.entity.ProductEntity;

public interface CartService {

	List<CartEntity> findCartList();

//	List<ProductEntity> findProductByBeerNo(Long beerNo);

//	List<CartEntity> findByBeerNo(Long beerNo);

	void addtoCartByBeerNo(CartEntity cartentity); // to click add to cart as a result into the value in the cartEntity

	void cartDelete(Long beerNo);
	

}
