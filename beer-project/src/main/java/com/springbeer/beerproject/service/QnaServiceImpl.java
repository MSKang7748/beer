package com.springbeer.beerproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbeer.beerproject.entity.Qna;
import com.springbeer.beerproject.repository.QnaRepository;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	QnaRepository qnaRepository;

	@Override
	public List<Qna> findQnaList() {
		List<Qna> qna = (List<Qna>)qnaRepository.findAll(); // 전체 검색
		return qna;
	}

	@Override
	public void writeqna(Qna qna) {
		qnaRepository.save(qna); // 질문 글 등록
		
	}

	@Override
	public List<Qna> qnadetail(int qnaNo) {
		List<Qna> qnadetail = qnaRepository.findByQnaNo(qnaNo);
		
		return qnadetail;
	}

	@Override
	public void qnadelete(int qnaNo) {
		qnaRepository.deleteById(qnaNo);
		
	}

	@Override
	public void updateqna(Qna qna) { // 질문 글 업데인트
		qnaRepository.save(qna);
		
	}



}
