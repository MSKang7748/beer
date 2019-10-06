package com.springbeer.beerproject.service;

import java.util.List;

import com.springbeer.beerproject.entity.Notice;
import com.springbeer.beerproject.entity.Qna;

public interface QnaService {

	List<Qna> findQnaList();
	List<Qna> qnadetail(int qnaNo);

	void writeqna(Qna qna);
	void qnadelete(int qnaNo);
	void updateqna(Qna qna);
	
}
