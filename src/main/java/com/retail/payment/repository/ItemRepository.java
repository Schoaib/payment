package com.retail.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.retail.payment.entity.Item;

/**
 * The Interface ItemRepository.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

	/**
	 * Find by id in.
	 *
	 * @param ls the ls
	 * @return the list
	 */
	List<Item> findByIdIn(long[] ls);

}