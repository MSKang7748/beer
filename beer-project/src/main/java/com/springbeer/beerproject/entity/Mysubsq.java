package com.springbeer.beerproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "mysubsq") // 내가 구독  버튼을 누르면 여기에 기록 된다.
@Data
public class Mysubsq {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "my_no")
	private int myNo;
	
	@Column(name = "member_id")
	private String memberId;

	@Column(nullable = true, name = "subsq_div")
	private String subsqDiv;
}
