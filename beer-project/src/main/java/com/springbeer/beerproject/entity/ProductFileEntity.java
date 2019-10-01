package com.springbeer.beerproject.entity;

import java.util.ArrayList;
import java.util.Collection;

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

import lombok.Data;

@Entity 
@Table(name="beerfile")
@Data
public class ProductFileEntity {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="file_no")
		private Long fileNo;
		
		@Column(name = "br_savedfilename")
		private String productSavedFileName;
		
		@Column(name = "br_userfilename")
		private String productUserFileName;
		
		@Column(name="beer_no")
		private Long beerNo;

}