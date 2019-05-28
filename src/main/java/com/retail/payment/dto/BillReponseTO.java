package com.retail.payment.dto;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new bill reponse TO.
 */
@Data
public class BillReponseTO {
	
	/** The customer id. */
	private Long customerId;
	
	/** The billed amount. */
	private double billedAmount;
	
	/** The net payable amount. */
	private double netPayableAmount;
}
