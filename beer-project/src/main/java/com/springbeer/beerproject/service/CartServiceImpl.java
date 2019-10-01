package com.springbeer.beerproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbeer.beerproject.entity.CartEntity;
import com.springbeer.beerproject.entity.ProductEntity;
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
	public void addToCart(CartEntity cart) {
		cartRepository.save(cart);
	}

	@Override
	public void addtoCartByMemberNo(CartEntity cartentity) {
		cartRepository.save(cartentity);
		
	}

	@Override
	public void cartDelete(String memberNo) {
		cartRepository.deleteById(memberNo);
		
	}

	@Override
	public List<CartEntity> cartFindByMemberNo(int memberNo) {
		List<CartEntity> cartinfo = cartRepository.findByMemberNo(memberNo);
		
		return cartinfo;
	}
	
	
	
}
