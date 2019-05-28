package com.retail.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.payment.entity.DiscountMaster;

/**
 * The Interface DiscountMasterRepository.
 */
public interface DiscountMasterRepository extends JpaRepository<DiscountMaster, Long> {

}