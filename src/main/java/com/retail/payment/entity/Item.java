package com.retail.payment.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
@Entity
public class Item {

	/** The id. */
	private @Id @GeneratedValue Long id;
	
	/** The name. */
	private String name;
	
	/** The price. */
	private double price;
	
	/** The is grocery. */
	private boolean isGrocery;

	/**
	 * Instantiates a new item.
	 *
	 * @param name the name
	 * @param price the price
	 * @param isGrocery the is grocery
	 */
	public Item(String name, double price, boolean isGrocery) {
		this.name = name;
		this.price = price;
		this.isGrocery = isGrocery;
	}

	/**
	 * Instantiates a new item.
	 */
	public Item() {
	}

}
