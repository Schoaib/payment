package com.retail.payment.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.retail.payment.common.CommonConstants;
import com.retail.payment.entity.Customer;
import com.retail.payment.entity.DiscountMaster;
import com.retail.payment.entity.Item;
import com.retail.payment.repository.CustomerRepository;
import com.retail.payment.repository.DiscountMasterRepository;
import com.retail.payment.repository.ItemRepository;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class LoadDatabase.
 */
@Configuration

/** The Constant log. */
@Slf4j
class LoadDatabase {

	/**
	 * Inits the database.
	 *
	 * @param customerRepository the customer repository
	 * @param discountMasterRepository the discount master repository
	 * @param itemRepository the item repository
	 * @return the command line runner
	 */
	@Bean
	CommandLineRunner initDatabase(CustomerRepository customerRepository,
			DiscountMasterRepository discountMasterRepository, ItemRepository itemRepository) {
		return args -> {
			// Load Customer Data
			log.info("Preloading " + customerRepository
					.save(new Customer("Shoaib", "Syed", "+9715288XXXX3", LocalDate.of(2016, 02, 20), false, false)));
			log.info("Preloading " + customerRepository
					.save(new Customer("Hasan", "Shoaib", "+9715288XXXX3", LocalDate.of(2018, 02, 20), false, false)));
			log.info("Preloading " + customerRepository
					.save(new Customer("Steve", "Job", "+9715288XXXX3", LocalDate.of(2016, 02, 20), true, false)));
			log.info("Preloading " + customerRepository
					.save(new Customer("John", "Wick", "+9715288XXXX3", LocalDate.of(2016, 02, 20), false, true)));
			// Load Discount Master
			log.info("Preloading " + discountMasterRepository
					.save(new DiscountMaster("Employee Discount", CommonConstants.EMPLOYEE_DISCOUNT, 30, 0, 0, false)));
			log.info("Preloading " + discountMasterRepository.save(
					new DiscountMaster("Affiliate  Discount", CommonConstants.AFFILIATE_DISCOUNT, 10, 0, 0, false)));
			log.info("Preloading " + discountMasterRepository.save(new DiscountMaster("2 Yrs old customer Discount",
					CommonConstants.OLD_CUSTOMER_DISCOUNT, 5, 0, 2, false)));
			log.info("Preloading " + discountMasterRepository
					.save(new DiscountMaster("General Discount", CommonConstants.GENERAL_DISCOUNT, 0, 5, 100, true)));
			// Load Item Data
			log.info("Preloading " + itemRepository.save(new Item("Nerf Gun", 160, false)));
			log.info("Preloading " + itemRepository.save(new Item("Bluetooth Spinner", 40, false)));
			log.info("Preloading " + itemRepository.save(new Item("Kids Ride", 360, false)));
			log.info("Preloading " + itemRepository.save(new Item("Oreo 500 Gms", 25, true)));
			log.info("Preloading " + itemRepository.save(new Item("Magnum Pack of 6", 24.5, true)));
		};
	}
}