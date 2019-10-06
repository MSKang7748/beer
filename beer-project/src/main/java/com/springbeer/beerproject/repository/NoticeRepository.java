package com.springbeer.beerproject.repository;

import org.springframework.data.repository.CrudRepository;

import com.springbeer.beerproject.entity.Notice;

public interface NoticeRepository extends CrudRepository<Notice, Long> { // 기본 CRUD가 수행 됨./ 사용하는 table명과 primary의 자료형을 명시 해 놓음.

}