package com.retail.payment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.payment.dto.BillReponseTO;
import com.retail.payment.dto.BillRequestTO;
import com.retail.payment.exception.PaymentException;
import com.retail.payment.service.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

// TODO: Auto-generated Javadoc
/**
 * The Class BillController.
 */
@RestController
@RequestMapping("/bill")
@Api(value = "bill", produces = "application/json")
public class BillController {

	/** The transaction service. */
	@Autowired
	private TransactionService transactionService;

	/**
	 * Preview bill.
	 *
	 * @param billRequest the bill request
	 * @return the bill reponse TO
	 * @throws PaymentException the payment exception
	 */
	@ApiOperation(value = "Saves the user deatils", response = BillReponseTO.class)
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Net payable amount returned successfully."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource."),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden."),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found.") })

	@PostMapping("/preview")
	public BillReponseTO previewBill(@RequestBody @Valid BillRequestTO billRequest) throws PaymentException {
		return transactionService.previewBill(billRequest);
	}

}
