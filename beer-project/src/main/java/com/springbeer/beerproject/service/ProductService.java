package com.springbeer.beerproject.service;

import java.util.Collection;
import java.util.List;

import com.springbeer.beerproject.entity.ProductEntity;
import com.springbeer.beerproject.entity.ProductFileEntity;

public interface ProductService {

	List<ProductEntity> findAllProducts();

	void writeProduct(ProductEntity productEntity);

	List<ProductEntity> findProductByBeerNo(Long beerNo);

	List<ProductFileEntity> findProductFileByBeerNo(Long beerNo);

	void productDelete(Long beerNo);

	void productImageDelete(Long beerNo);

	void insertProductFile(ProductFileEntity productFile);

	void updateProduct(ProductEntity productEntity);
	


}