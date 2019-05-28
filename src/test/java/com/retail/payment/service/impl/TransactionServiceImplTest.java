package com.retail.payment.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.retail.payment.common.CommonConstants;
import com.retail.payment.dto.BillReponseTO;
import com.retail.payment.dto.BillRequestTO;
import com.retail.payment.entity.Customer;
import com.retail.payment.entity.DiscountMaster;
import com.retail.payment.entity.Item;
import com.retail.payment.exception.PaymentException;
import com.retail.payment.repository.CustomerRepository;
import com.retail.payment.repository.DiscountMasterRepository;
import com.retail.payment.repository.ItemRepository;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionServiceImplTest.
 */
@RunWith(JMockit.class)
public class TransactionServiceImplTest {

	/** The transaction service impl. */
	@Tested
	private TransactionServiceImpl transactionServiceImpl;

	/** The customer repository. */
	@Injectable
	private CustomerRepository customerRepository;

	/** The discount master repository. */
	@Injectable
	private DiscountMasterRepository discountMasterRepository;

	/** The item repository. */
	@Injectable
	private ItemRepository itemRepository;

	/** The bill request. */
	BillRequestTO billRequest;

	/** The bill reponse. */
	BillReponseTO billReponse;

	/** The customer. */
	Customer customer;

	/** The item list. */
	List<Item> itemList;

	/** The discount list. */
	List<DiscountMaster> discountList;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		int[] itemArray = { 1, 2, 3 };
		billRequest = new BillRequestTO();
		billRequest.setCustomerId(1);
		billRequest.setItems(itemArray);

		customer = new Customer();
		customer.setId(1L);
		customer.setDateOfJoining(LocalDate.now());
		customer.setEmployee(false);
		customer.setAffiliate(false);

		itemList = new ArrayList<Item>();
		itemList.add(new Item("Item 1", 195, false));
		itemList.add(new Item("Item 1", 100, true));

		discountList = new ArrayList<DiscountMaster>();
	}

	/**
	 * Test preview bill for customer not found.
	 *
	 * @throws PaymentException the payment exception
	 */
	@Test
	public void testPreviewBillForCustomerNotFound() throws PaymentException {
		Optional<Customer> optionalCcustomer = Optional.empty();
		new NonStrictExpectations() {
			{
				customerRepository.findById(1L);
				returns(optionalCcustomer);
			}
		};

		try {
			billReponse = transactionServiceImpl.previewBill(billRequest);
		} catch (PaymentException paymentException) {
			assertEquals("Customer not found with id " + billRequest.getCustomerId(), paymentException.getMessage());
		}
	}

	/**
	 * Test preview bill for general discount.
	 *
	 * @throws PaymentException the payment exception
	 */
	@Test
	public void testPreviewBillForGeneralDiscount() throws PaymentException {

		discountList.add(new DiscountMaster("General Discount", CommonConstants.GENERAL_DISCOUNT, 0, 5, 100, true));

		new NonStrictExpectations() {
			{
				customerRepository.findById(customer.getId());
				returns(Optional.of(customer));
			}
			{

				itemRepository.findByIdIn(Arrays.stream(billRequest.getItems()).asLongStream().toArray());
				returns(itemList);
			}
			{
				discountMasterRepository.findAll();
				returns(discountList);
			}
		};

		billReponse = transactionServiceImpl.previewBill(billRequest);

		assertTrue("Customer Id should be equal to 1L", billReponse.getCustomerId() == 1L);
		assertTrue("Billed Amount should be equal to 295", billReponse.getBilledAmount() == 295);
		assertTrue("Net Payable Amount should be equal to 285.0", billReponse.getNetPayableAmount() == 285);
	}

	/**
	 * Test preview bill for employee and general discount.
	 *
	 * @throws PaymentException the payment exception
	 */
	@Test
	public void testPreviewBillForEmployeeAndGeneralDiscount() throws PaymentException {

		discountList.add(new DiscountMaster("General Discount", CommonConstants.GENERAL_DISCOUNT, 0, 5, 100, true));
		discountList.add(new DiscountMaster("Employee Discount", CommonConstants.EMPLOYEE_DISCOUNT, 30, 0, 0, false));

		customer.setEmployee(true);

		new NonStrictExpectations() {
			{
				customerRepository.findById(customer.getId());
				returns(Optional.of(customer));
			}
			{

				itemRepository.findByIdIn(Arrays.stream(billRequest.getItems()).asLongStream().toArray());
				returns(itemList);
			}
			{
				discountMasterRepository.findAll();
				returns(discountList);
			}
		};

		billReponse = transactionServiceImpl.previewBill(billRequest);

		assertTrue("Customer Id should be equal to 1L", billReponse.getCustomerId() == 1L);
		assertTrue("Billed Amount should be equal to 295", billReponse.getBilledAmount() == 295);
		assertTrue("Net Payable Amount should be equal to 285.0", billReponse.getNetPayableAmount() == 226.5);
	}
	
	@Test
	public void testPreviewBillForAffiliateAndGeneralDiscount() throws PaymentException {

		discountList.add(new DiscountMaster("General Discount", CommonConstants.GENERAL_DISCOUNT, 0, 5, 100, true));
		discountList.add(new DiscountMaster("Employee Discount", CommonConstants.AFFILIATE_DISCOUNT, 10, 0, 0, false));

		customer.setAffiliate(true);

		new NonStrictExpectations() {
			{
				customerRepository.findById(customer.getId());
				returns(Optional.of(customer));
			}
			{

				itemRepository.findByIdIn(Arrays.stream(billRequest.getItems()).asLongStream().toArray());
				returns(itemList);
			}
			{
				discountMasterRepository.findAll();
				returns(discountList);
			}
		};

		billReponse = transactionServiceImpl.previewBill(billRequest);

		assertTrue("Customer Id should be equal to 1L", billReponse.getCustomerId() == 1L);
		assertTrue("Billed Amount should be equal to 295", billReponse.getBilledAmount() == 295);
		assertTrue("Net Payable Amount should be equal to 285.0", billReponse.getNetPayableAmount() == 265.5);
	}
	
	@Test
	public void testPreviewBillForOldCustomerAndGeneralDiscount() throws PaymentException {

		discountList.add(new DiscountMaster("General Discount", CommonConstants.GENERAL_DISCOUNT, 0, 5, 100, true));
		discountList.add(new DiscountMaster("Employee Discount", CommonConstants.OLD_CUSTOMER_DISCOUNT, 5, 0, 2, false));

		customer.setDateOfJoining(LocalDate.now().minusYears(3));

		new NonStrictExpectations() {
			{
				customerRepository.findById(customer.getId());
				returns(Optional.of(customer));
			}
			{

				itemRepository.findByIdIn(Arrays.stream(billRequest.getItems()).asLongStream().toArray());
				returns(itemList);
			}
			{
				discountMasterRepository.findAll();
				returns(discountList);
			}
		};

		billReponse = transactionServiceImpl.previewBill(billRequest);

		assertTrue("Customer Id should be equal to 1L", billReponse.getCustomerId() == 1L);
		assertTrue("Billed Amount should be equal to 295", billReponse.getBilledAmount() == 295);
		assertTrue("Net Payable Amount should be equal to 285.0", billReponse.getNetPayableAmount() == 275.25);
	}

}
