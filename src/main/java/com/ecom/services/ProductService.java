package com.ecom.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecom.dto.ItemDTO;
import com.ecom.entity.Item;
import com.ecom.repository.ItemRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProductService {

	private ItemRepository itemRepository;
	
	@Autowired
	public ProductService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public List<Item> getAllProducts() {
		return itemRepository.findAll();
	}

	public ResponseEntity<String> addProduct(ItemDTO itemDto) throws ObjectNotFoundException {
		if(itemDto.equals(null)) throw new ObjectNotFoundException("Object Not Found");
		Item item= new Item();
		item.setItemName(itemDto.getItemName());
		item.setItemTotal(itemDto.getItemTotal());
		item.setQuantity(itemDto.getQuantity());
		item.setCategoryName(itemDto.getCategoryName());
		itemRepository.save(item);
		return ResponseEntity.ok("Product Added to Database");
	}

	public Optional<Item> getProduct(int id) {
		return itemRepository.findById(id);
	}

	public ResponseEntity<String> deleteProduct(int id) {
		itemRepository.deleteById(id);
		return ResponseEntity.ok("Deleted Successfully");
	}

	public List<Item> searchItem(String name) {
		return itemRepository.findByName(name);
	}
	
	

}
