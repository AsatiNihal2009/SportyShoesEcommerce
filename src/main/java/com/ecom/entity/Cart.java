package com.ecom.entity;

import java.util.Date;



import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cartId")
	private Integer cartId;
	
	@Column(name = "total")
	private double price;
	
	@Column(name="date")
	private Date purchasedDate;
	
	@OneToMany(mappedBy = "cart")
	@JsonIgnore
	private List<Item> items;
	
	@OneToOne
	@MapsId
	@JsonIgnore
	private User user;
	
}
