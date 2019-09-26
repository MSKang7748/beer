package com.springbeer.beerproject.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.springbeer.beerproject.entity.ProductEntity;
import com.springbeer.beerproject.entity.ProductFileEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
	
	List<ProductEntity> findByBeerNo(Long beerNo);

	@Query(value = "SELECT pfe " + 
				   "FROM ProductFileEntity as pfe " + 
				   "WHERE pfe.beerNo = :beerNo", nativeQuery = false)
	List<ProductFileEntity> findFileByBeerNo(@Param("beerNo") Long beerNo);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM ProductEntity as pe " + 
			   	   "WHERE pe.beerNo = :beerNo")
	void deleteById(@Param("beerNo") Long beerNo);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM ProductFileEntity as pfe " + 
		   	   	   "WHERE pfe.beerNo = :beerNo")
	void deleteImageById(@Param("beerNo")Long beerNo);

//	@Query(value = "INSERT INTO productFile(fileNo, productSavedFileName, productUserFileName) " +
//				   "VALUES(productFile.fileNo, productFile.productSavedFileName, productFile.productUserFileName)")
	void save(@Param("productFile")ProductFileEntity productFile);
	
//	@Modifying
//	@Transactional
//	@Query(value = "UPDATE ProductEntity as pe " +
//				   "SET pe.beerName = :#{#productEntity.beerName} " + 
//				   "pe.beerContent = :#{#productEntity.beerContent} " + 
//				   "pe.beerdiv = :#{#productEntity.beerDiv} " + 
//				   "pe.beerPrice = :#{#productEntity.beerPrice} " + 
//			   	   "WHERE pe.beerNo = :#{#productEntity.beerNo}")
//	void modifyProduct(@Param("productEntity") ProductEntity productEntity);

}
