package com.springbeer.beerproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity 
@Table(name="lectureboardfile")
@Data
public class LectureBoardFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="file_no")
	private int fileNo;
	
	@Column(name = "subsq_savedfilename")
	private String subsqSavedFileName;
	
	@Column(name = "subsq_userfilename")
	private String subsqUserFileName;
	
	@Column(name="board_no")
	private int boardNo;

}