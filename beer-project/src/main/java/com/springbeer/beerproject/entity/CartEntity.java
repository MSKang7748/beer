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
@Table(name = "cart")
@Data
public class CartEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_id")
	private int cartId;
	
	@Column(nullable = false)
	private String id;
	
	@Column(name = "beer_no")
	private Long beerNo;
	
	@Column(name = "cart_cnt")
	private int cartCnt;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="beer_no") 
	private Collection<ProductFileEntity> productFileEntity;

}
