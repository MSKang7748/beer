package com.springbeer.beerproject.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import lombok.Data;

@Entity
@Table(name="QnA")
@Data
public class Qna {
	
	@Id // primary Key 역할
	@GeneratedValue(strategy = GenerationType.AUTO) // 자동증가 처리
	@Column(name="qna_no")
	private int qnaNo;
	
	@Column(nullable = false) 
	private String memberId;
	
	@Column(name = "qna_title")
	private String qnaTitle;
	
	@Column(name = "qna_content")
	private String qnaContent;
	
	@Column(name = "qna_div")
	private String qnaDiv; // 분류
	
	@Column(name = "qna_date")
	private Date qnaDate = new Date(); // 기본 값 설정

	@Column(name = "ans_content")
	private String ansContent;
	
	@Column(name = "ans_bool", nullable=false) // 1과 0으로 바뀌게 함으로써 표기 할 생각임. (boolean을 적용 할 수 없다)
	private int ansCheck = 0;
	
}