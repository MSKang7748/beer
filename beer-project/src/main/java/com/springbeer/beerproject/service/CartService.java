package com.springbeer.beerproject.service;

import java.util.List;

import com.springbeer.beerproject.entity.CartEntity;
import com.springbeer.beerproject.entity.ProductEntity;

public interface CartService {

	List<CartEntity> findCartList();

//	List<ProductEntity> findProductByBeerNo(Long beerNo);

//	List<CartEntity> findByBeerNo(Long beerNo);

	List<CartEntity> cartFindByBeerNo(Long beerNo);


}
