package com.springbeer.beerproject.repository;

import org.springframework.data.repository.CrudRepository;

import com.springbeer.beerproject.entity.Lecture;
import com.springbeer.beerproject.entity.Notice;

public interface NoticeRepository extends CrudRepository<Notice, Long> {

	Notice findByid(Long id);
	
}