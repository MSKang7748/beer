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
@Table(name="lectureboard")
@Data
public class LectureBoard {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO  )	
	@Column(name="board_no")
	private int boardNo;
	
	@Column(name="board_title")
	private String boardTitle;
	
	@Column(name="board_content")
	private String boardContent;
	
	@Column(nullable=false)
	private Date createdDatetime = new Date();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 해당 entity가 만들어지면 fileList는 자동으로 조회가 됨 = eager
	@JoinColumn(name="board_no") // subsq_no를 file에서 외래키로 받는다.
	private Collection<LectureBoardFile> lectureboardfile;
	
}
