package com.retail.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.payment.entity.Customer;

/**
 * The Interface CustomerRepository.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}