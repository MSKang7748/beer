package com.springbeer.beerproject.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity // table로 만들어 주는 것
@Data
public class Notice {
	
	@Id // primary Key 역할
	@GeneratedValue // sequence or auto_increment 역할
	private Long id;
	private String title;
	private String content;
	private String tags;
	private Date updatedDate = new Date(); // 기본 값 설정
}