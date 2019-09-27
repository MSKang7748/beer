package com.springbeer.beerproject.service;

import java.util.List;

 
import com.springbeer.beerproject.entity.MemberEntity;

public interface MemberService {

	List<MemberEntity> findAllMembers();

	void registerMember(MemberEntity member);

	MemberEntity findMemberById(String memberId);	
	MemberEntity findMemberByIdAndPasswd(String memberId, String passwd);
	
	void unregisterMember(String memberId);

	void modifyMember(MemberEntity member);

	MemberEntity findByMemberId(String memberId);

	

 

 

}
