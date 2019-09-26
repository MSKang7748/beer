package com.springbeer.beerproject.entity;

import java.util.Collection;

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
@Table(name = "Mypage")
@Data
public class Mypage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reg_no")
	private int regNo;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "div")
	private String div;

	@Column
	private String id;

}
