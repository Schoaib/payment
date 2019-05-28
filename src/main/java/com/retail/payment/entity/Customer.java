package com.retail.payment.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
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
public class Customer {
	
	/** The id. */
	private @Id @GeneratedValue Long id;
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The mobile. */
	private String mobile;
	
	/** The date of joining. */
	private LocalDate dateOfJoining;
	
	/** The is employee. */
	private boolean isEmployee;
	
	/** The is affiliate. */
	private boolean isAffiliate;

	/**
	 * Instantiates a new customer.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param mobile the mobile
	 * @param dateOfJoining the date of joining
	 * @param isEmployee the is employee
	 * @param isAffiliate the is affiliate
	 */
	public Customer(String firstName, String lastName, String mobile, LocalDate dateOfJoining, boolean isEmployee,
			boolean isAffiliate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.dateOfJoining = dateOfJoining;
		this.isEmployee = isEmployee;
		this.isAffiliate = isAffiliate;
	}

	/**
	 * Instantiates a new customer.
	 */
	public Customer() {
	}
}
