package com.springbeer.beerproject.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Entity
@Table(name="member")
@Data
public class MemberEntity {

	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO  )	
	@Column   	
	private int MemberNo;	
	
	@Column(nullable=false)
	private String memberId;
	
	@Column(nullable=false)
	private String passwd;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String phone;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String userType;
	
	@Column(nullable=false)
	private Date createdDatetime = new Date();		
}
