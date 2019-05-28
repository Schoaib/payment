package com.retail.payment.service;

import com.retail.payment.dto.BillReponseTO;
import com.retail.payment.dto.BillRequestTO;
import com.retail.payment.exception.PaymentException;

// TODO: Auto-generated Javadoc
/**
 * The Interface TransactionService.
 */
public interface TransactionService {
	
	/**
	 * Preview bill.
	 *
	 * @param billRequest the bill request
	 * @return the bill reponse TO
	 * @throws PaymentException the payment exception
	 */
	BillReponseTO previewBill(BillRequestTO billRequest) throws PaymentException;
}
