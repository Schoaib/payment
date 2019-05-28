package com.retail.payment.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Builder.Default;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new bill request TO.
 */
@Data
public class BillRequestTO {

	/** The customer id. */
	@NotNull
	private int customerId;
	
	/** The items. */
	@NotNull
	private int[] items;

}
