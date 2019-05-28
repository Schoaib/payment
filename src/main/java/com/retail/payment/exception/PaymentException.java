package com.retail.payment.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class PaymentException.
 */
public class PaymentException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 272275072123723695L;

	/**
	 * Instantiates a new payment exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public PaymentException(String message, Throwable e) {
		super(message, e);
	}

	/**
	 * Instantiates a new payment exception.
	 *
	 * @param message the message
	 */
	public PaymentException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new payment exception.
	 *
	 * @param e the e
	 */
	public PaymentException(Throwable e) {
		super(e);
	}
}
