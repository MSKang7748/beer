package com.springbeer.beerproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.springbeer.beerproject.entity.MemberEntity;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
 

public interface MemberRepository extends CrudRepository<MemberEntity, Integer> {
	
	MemberEntity findByMemberIdAndPasswd(String memberId, String passwd);

	MemberEntity findByMemberId(String memberId);

	
}
