package com.springbeer.beerproject.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbeer.beerproject.entity.ProductEntity;
import com.springbeer.beerproject.entity.ProductFileEntity;
import com.springbeer.beerproject.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<ProductEntity> findAllProducts() {
	
		return (List<ProductEntity>)productRepository.findAll();
	}

	@Override
	public void writeProduct(ProductEntity productEntity) {
	
		productRepository.save(productEntity);
		
	}

	@Override
	public List<ProductEntity> findProductByBeerNo(Long beerNo) {
		
		List<ProductEntity> products = productRepository.findByBeerNo(beerNo);
		
		return products;
	}

	@Override
	public List<ProductFileEntity> findProductFileByBeerNo(Long beerNo) {
		
		List<ProductFileEntity> productFile = productRepository.findFileByBeerNo(beerNo);
		
		return productFile;
	}

	@Override
	public void productDelete(Long beerNo) {
		
		productRepository.deleteById(beerNo);
		
	}

	@Override
	public void productImageDelete(Long beerNo) {

		productRepository.deleteImageById(beerNo);
		
	}

	@Override
	public void insertProductFile(ProductFileEntity productFile) {
		
		productRepository.save(productFile);
		
	}

	@Override
	public void updateProduct(ProductEntity productEntity) {
		
		productRepository.save(productEntity);
		
	}
	
	


	
}