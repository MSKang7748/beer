package com.springbeer.beerproject.entity;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Entity
@Table(name="member")
@Data
public class MemberEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO  )	
	@Column(name="member_no")
	private int memberNo;
	
	@Column(name="member_id")
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

	@OneToMany(mappedBy="member")
	private List<ProductEntity> productEntity;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 해당 entity가 만들어지면 fileList는 자동으로 조회가 됨 = eager
	@JoinColumn(name="member_no") 
	private Collection<Subscription> subscription;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 해당 entity가 만들어지면 fileList는 자동으로 조회가 됨 = eager
	@JoinColumn(name="member_no") 
	private Collection<Lecture> lecture;
}
