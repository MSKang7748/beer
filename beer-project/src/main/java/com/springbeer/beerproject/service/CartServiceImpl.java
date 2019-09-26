package com.springbeer.beerproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbeer.beerproject.entity.CartEntity;
import com.springbeer.beerproject.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartRepository cartRepository;

	@Override
	public List<CartEntity> findCartList() {
		List<CartEntity> cartEntity = (List<CartEntity>)cartRepository.findAll();
		return cartEntity;
	}

	@Override
	public List<CartEntity> cartFindByBeerNo(Long beerNo) {
		
		List<CartEntity> cartEntity = cartRepository.findById(beerNo);
		
		return cartEntity;
	}

	
	
	
}
