package com.springbeer.beerproject.repository;

import org.springframework.data.repository.CrudRepository;

import com.springbeer.beerproject.entity.MemberEntity;

import org.springframework.data.jpa.repository.Query;
 

public interface MemberRepository extends CrudRepository<MemberEntity, String> {
	//MemberEntity selectMemberByIdAndPasswd(String memberId, String passwd);

}
