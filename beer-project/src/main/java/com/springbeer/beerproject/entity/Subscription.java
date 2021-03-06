package com.springbeer.beerproject.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="subscription")
@Data
public class Subscription {
	
	@Id // primary Key 역할
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="subsq_no")
	private int subsqNo;
	
	@Column(name = "subsq_date")
	private Date subsqDate = new Date(); // 기본 값 설정
	
	@Column(name="member_no")
	private int memberNo;
	
	@Column(name="lecture_no")
	private int lectureNo;
	
}