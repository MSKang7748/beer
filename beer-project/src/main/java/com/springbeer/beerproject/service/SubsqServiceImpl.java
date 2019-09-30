package com.springbeer.beerproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbeer.beerproject.entity.LectureBoardFile;
import com.springbeer.beerproject.entity.Subscription;
import com.springbeer.beerproject.repository.SubsqRepository;

@Service
public class SubsqServiceImpl implements SubsqService {
	
	@Autowired
	SubsqRepository subsqRepository;

	

}
