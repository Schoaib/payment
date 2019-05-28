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
public class DiscountMaster {
	
	/** The id. */
	private @Id @GeneratedValue Long id;
	
	/** The name. */
	private String name;
	
	/** The identifier. */
	private String identifier;
	
	/** The percentage. */
	private double percentage;
	
	/** The amount. */
	private double amount;
	
	/** The condition. */
	private double condition;
	
	/** The is grocery discount. */
	private boolean isGroceryDiscount;

	/**
	 * Instantiates a new discount master.
	 *
	 * @param name the name
	 * @param identifier the identifier
	 * @param percentage the percentage
	 * @param amount the amount
	 * @param condition the condition
	 * @param isGroceryDiscount the is grocery discount
	 */
	public DiscountMaster(String name, String identifier, double percentage, double amount, double condition,
			boolean isGroceryDiscount) {
		this.name = name;
		this.identifier = identifier;
		this.percentage = percentage;
		this.amount = amount;
		this.condition = condition;
		this.isGroceryDiscount = isGroceryDiscount;

	}

	/**
	 * Instantiates a new discount master.
	 */
	public DiscountMaster() {
	}
}
