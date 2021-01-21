package com.ecom.controller;

import java.text.ParseException;
import java.util.List;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entity.Cart;
import com.ecom.entity.User;
import com.ecom.services.UserService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getUser() {
		return userService.getAllUser();
	}
	
	@GetMapping("/{id}")
	public Optional<User> getUser(@PathVariable(value = "id")int id) {
		return userService.getUser(id);
	}
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public String addUser(@RequestParam String email,@RequestParam String password) {
		return userService.addUser(email,password);
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> updateAdminPassword(@PathVariable(value ="id")int id,@RequestParam String password) throws NotFoundException{
		return userService.updateAdminPassword(id,password);
	}
	
	@GetMapping("/search/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Cart> getPurchaseHistoryBasedOnfilters(@PathVariable(value = "id")int id,
			@RequestParam(value = "category")String category,
			@RequestParam(value = "date")String date) throws ParseException{
		return userService.getAllProductsInCart(id,category,date);
	}
	
}
