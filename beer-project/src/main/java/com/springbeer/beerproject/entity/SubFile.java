package com.springbeer.beerproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity 
@Table(name="subfile")
@Data
public class SubFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="sub_no")
	private int subNo;
	
	@Column(name = "subsq_savedfilename")
	private String subsqSavedFileName;
	
	@Column(name = "subsq_userfilename")
	private String subsqUserFileName;

}