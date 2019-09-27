package com.springbeer.beerproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbeer.beerproject.common.Util;
import com.springbeer.beerproject.entity.MemberEntity;
import com.springbeer.beerproject.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public void registerMember(MemberEntity member) {
		
		memberRepository.save(member);
		
	}

	@Override
	public List<MemberEntity> findAllMembers() {
		
		return (List<MemberEntity>)memberRepository.findAll();
		
	}


	
	@Override
	public MemberEntity findMemberByIdAndPasswd(String memberId, String passwd) {
		MemberEntity member = memberRepository.findByMemberIdAndPasswd(memberId, passwd);
		return member;
	}

	@Override
	public MemberEntity findByMemberId(String memberId) {
			
		MemberEntity mp = memberRepository.findByMemberId(memberId);
		return mp;
	}
	
      ///////////////////////////////
	@Override
	public void unregisterMember(String memberId) {
		  //memberRepository.deleteById(memberId);   
	}

	@Override
	public void modifyMember(MemberEntity member) {
		memberRepository.save(member);  		
	}

	@Override
	public MemberEntity findMemberById(String memberId) {
		MemberEntity memberinfo = memberRepository.findByMemberId(memberId);
		
		return memberinfo;
	}

	@Override
	public void updateMember(MemberEntity member) {
		memberRepository.save(member);
		
	}
	
	
}
