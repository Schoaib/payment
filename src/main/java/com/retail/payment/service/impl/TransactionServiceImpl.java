package com.retail.payment.service.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.retail.payment.service.TransactionService;


@Service
public class TransactionServiceImpl implements TransactionService {

	/** The customer repository. */
	@Autowired
	private CustomerRepository customerRepository;

	/** The discount master repository. */
	@Autowired
	private DiscountMasterRepository discountMasterRepository;

	/** The item repository. */
	@Autowired
	private ItemRepository itemRepository;

	/**
	 * Preview bill.
	 *
	 * @param billRequest the bill request
	 * @return the bill reponse TO
	 * @throws PaymentException the payment exception
	 */
	@Override
	public BillReponseTO previewBill(BillRequestTO billRequest) throws PaymentException {
		Optional<Customer> customer = customerRepository.findById((long) billRequest.getCustomerId());
		if (!customer.isPresent())
			throw new PaymentException("Customer not found with id " + billRequest.getCustomerId());
		List<Item> itemList = itemRepository.findByIdIn(Arrays.stream(billRequest.getItems()).asLongStream().toArray());
		List<DiscountMaster> discountMasterList = discountMasterRepository.findAll();
		return applyPercentageDiscount(customer.get(), itemList, discountMasterList);
	}

	/**
	 * Apply general discount.
	 *
	 * @param customer                the customer
	 * @param discountMasterList      the discount master list
	 * @param itemList                the item list
	 * @param perentageDiscountAmount the perentage discount amount
	 * @return the bill reponse TO
	 */
	private BillReponseTO applyGeneralDiscount(Customer customer, List<DiscountMaster> discountMasterList,
			List<Item> itemList, double perentageDiscountAmount) {
		DiscountMaster generalDiscount = discountMasterList.stream()
				.filter(discount -> discount.getIdentifier().equals(CommonConstants.GENERAL_DISCOUNT)).findFirst()
				.get();
		double billAmount = itemList.stream().mapToDouble(Item::getPrice).sum();
		int remainder = (int) (billAmount / generalDiscount.getCondition());
		double discountAmount = remainder * generalDiscount.getAmount();
		BillReponseTO billResponse = new BillReponseTO();
		billResponse.setBilledAmount(billAmount);
		billResponse.setCustomerId(customer.getId());
		billResponse.setNetPayableAmount(billAmount - discountAmount - perentageDiscountAmount);
		return billResponse;
	}

	/**
	 * Apply percentage discount.
	 *
	 * @param customer           the customer
	 * @param itemList           the item list
	 * @param discountMasterList the discount master list
	 * @return the bill reponse TO
	 */
	private BillReponseTO applyPercentageDiscount(Customer customer, List<Item> itemList,
			List<DiscountMaster> discountMasterList) {
		double percentageDiscount = 0;
		double billAmountWithoutGrocery = itemList.stream().filter(item -> !item.isGrocery()).mapToDouble(Item::getPrice)
				.sum();
		if (customer.isEmployee()) {
			percentageDiscount = discountMasterList.stream()
					.filter(discount -> discount.getIdentifier().equals(CommonConstants.EMPLOYEE_DISCOUNT)).findFirst()
					.get().getPercentage();
		} else if (customer.isAffiliate()) {
			percentageDiscount = discountMasterList.stream()
					.filter(discount -> discount.getIdentifier().equals(CommonConstants.AFFILIATE_DISCOUNT)).findFirst()
					.get().getPercentage();
		} else if (LocalDate.now().minusYears(2).isAfter(customer.getDateOfJoining())) {
			percentageDiscount = discountMasterList.stream()
					.filter(discount -> discount.getIdentifier().equals(CommonConstants.OLD_CUSTOMER_DISCOUNT))
					.findFirst().get().getPercentage();
		}
		return applyGeneralDiscount(customer, discountMasterList, itemList,
				billAmountWithoutGrocery * percentageDiscount / 100);
	}

}
