package com.springbeer.beerproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbeer.beerproject.entity.SubFile;
import com.springbeer.beerproject.entity.Subscription;
import com.springbeer.beerproject.repository.SubsqRepository;

@Service
public class SubsqServiceImpl implements SubsqService {
	
	@Autowired
	SubsqRepository subsqRepository;

	@Override
	public List<Subscription> findSubsqList() {
		List<Subscription> subsq = (List<Subscription>)subsqRepository.findAll();
		return subsq;
	}

	@Override
	public void writeSubsq(Subscription subscription) {
		subsqRepository.save(subscription);
		
	}

	@Override
	public List<Subscription> subsqdetail(int subsqNo) {
		List<Subscription> detail = subsqRepository.findById(subsqNo);
		
		return detail;
	}

	@Override
	public List<SubFile> subsqDetailFileListBySubsqNo(Subscription subscription) {
		List<SubFile> detailBySubsqNo = subsqRepository.findById(subscription);
		
		return detailBySubsqNo; 
	}


	@Override

	public List<Subscription> findSubsqList(int subsqDate) {
//		List<Subscription> subsq = (List<Subscription>)subsqRepository.findSubsqListByDate(subsqDate); 
//		return subsq;
		return null;
	}

	public List<Subscription> subsqupdate(int subsqNo) {
		List<Subscription> update = subsqRepository.findById(subsqNo);
		return update;
	}
	
	@Override
	public void subsqdelete(int subsqNo) {
		subsqRepository.deleteById(subsqNo);

	}


	@Override
	public void subsqupdatewrite(Subscription subscription) {

		subsqRepository.save(subscription);
	}



}
