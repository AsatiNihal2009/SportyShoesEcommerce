package com.ecom.entity;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "itemId")
	private Integer itemId;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "itemName")
	private String itemName;
	
	@Column(name = "itemTotal")
	private double itemTotal;
	
	@ManyToOne
	@JoinColumn(name = "cartId")
	private Cart cart;
	
	@Column(name = "categoryName")
	private String categoryName;
	
	
	
	@Column(name = "isDeleted",columnDefinition = "BOOLEAN default 0")
	private Boolean isDeletedFromCart;
	
}
