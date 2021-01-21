package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	@Query("from Item where itemName=?1")
	List<Item> findByName(String name);

}
