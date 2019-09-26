package com.springbeer.beerproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springbeer.beerproject.entity.SubFile;
import com.springbeer.beerproject.entity.Subscription;

public interface SubsqRepository extends CrudRepository<Subscription, Integer> { // 기본 CRUD가 수행 됨./ 사용하는 table명과 primary의 자료형을 명시 해 놓음.

		List<Subscription> findAll();
		
		List<Subscription> findById(int subsqNo);
		List<SubFile> findById(Subscription subscription);

}