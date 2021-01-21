package com.ecom.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	@Query("from Cart c ")
	List<Cart> findbyIdentity();

	@Query("from Cart c where c.purchasedDate=?1")
	List<Cart> findByDate(Date date);

	@Query("from Cart c inner join c.items item where item.categoryName=?1")
	List<Cart> findByCategory(String category);

	@Query("from Cart c inner join c.items item where item.categoryName=?1 and c.purchasedDate=?2")
	List<Cart> findByDateAndCategory(Date date,String category);

}
