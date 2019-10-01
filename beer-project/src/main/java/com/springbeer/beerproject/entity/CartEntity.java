package com.springbeer.beerproject.entity;

import java.util.Collection;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Entity
@Table(name = "cart")
@Data
public class CartEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_id")
	private int cartId;

	@Column(name = "member_no", nullable = false)
	private int memberNo;

	@Column(name = "beer_no")
	private Long beerNo;

	@Column(name = "cart_cnt")
	private int cartCnt;

	@Column(name = "cart_date")
	private Date cartDate = new Date();

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "beer_no", insertable = false, updatable = false)
	private ProductEntity product;

}
