package com.ecom.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.ItemDTO;
import com.ecom.entity.Item;
import com.ecom.services.ProductService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/product")
public class ProductController {

	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GetMapping
	public List<Item> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> addItem(@RequestBody ItemDTO item) throws ObjectNotFoundException {
		return productService.addProduct(item);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Optional<Item> getProduct(@PathVariable(value = "id")int id) {
		return productService.getProduct(id);
	}
	
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteItem(@PathVariable(value = "id")int id) {
		return productService.deleteProduct(id);
	}
	
	@GetMapping("/search")
	public List<Item> searchItem(@RequestParam String name){
		return productService.searchItem(name);
	}
}
