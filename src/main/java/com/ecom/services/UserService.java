package com.ecom.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecom.entity.Cart;
import com.ecom.entity.User;
import com.ecom.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class UserService {

	private UserRepository repository;
		
	private CartService  cartService;
	
	@Autowired
	public UserService(UserRepository repository,CartService cartService){
		this.repository = repository;
		this.cartService=cartService;
	}

	public List<User> getAllUser() {
		return repository.findAll();
	}

	public Optional<User> getUser(int id) {
		System.out.println(repository.findById(id));
		return repository.findById(id);
	}

	public ResponseEntity<String> updateAdminPassword(int id, String password) throws NotFoundException {
		Optional<User> user=repository.findById(id);
		if(password.isBlank()) throw new NotFoundException("Password Not Found"); 
		else user.get().setPassword(password);
		repository.save(user.get());
		return ResponseEntity.ok("Updated");
	}

	public List<Cart> getAllProductsInCart(int id, String category, String date) throws ParseException {
		Optional<User> user = repository.findById(id);
	    Date date1=new SimpleDateFormat("dd-MM-yyyy HH:MM:SS").parse(date);  
		if(category.isBlank()) return cartService.findByDate(date1);
		if(date==null) return cartService.findByCategory(category);
		else return cartService.findByDateAndCategory(date1, category);
	}

	public String addUser(String email,String password) {

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		repository.save(user);
		return "User Added";
	}
	
}
