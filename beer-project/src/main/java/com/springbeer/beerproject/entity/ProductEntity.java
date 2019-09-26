package com.springbeer.beerproject.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
@Table(name = "beer")
@Data
public class ProductEntity {

	@Id
	@Column(name = "beer_no")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long beerNo;
	
	@Column(name = "beer_name")
	private String beerName;
	
	@Column(name = "beer_content")
	private String beerContent;
	
	@Column(name = "beer_div")
	private String beerDiv;
	
	@Column(name = "beer_price")
	private int beerPrice;
	
	@Column
	private String id;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="beer_no") 
	private Collection<ProductFileEntity> productFileEntity;
	
}
