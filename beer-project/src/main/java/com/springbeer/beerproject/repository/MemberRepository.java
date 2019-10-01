package com.springbeer.beerproject.repository;

import org.springframework.data.repository.CrudRepository;

import com.springbeer.beerproject.entity.MemberEntity;
 

public interface MemberRepository extends CrudRepository<MemberEntity, Integer> {
	
	MemberEntity findByMemberIdAndPasswd(String memberId, String passwd);

	MemberEntity findByMemberId(String memberId);

	
}
