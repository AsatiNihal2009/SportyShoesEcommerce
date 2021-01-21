package com.ecom.services;

import java.util.ArrayList;

import java.util.Date;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entity.Cart;
import com.ecom.entity.Item;
import com.ecom.entity.User;
import com.ecom.repository.CartRepository;
import com.ecom.repository.UserRepository;

@Service
public class CartService {
	
	private CartRepository cartRepository;
	
	private ProductService productService;
	
	private UserRepository userRepository;
	
	@Autowired
	public CartService(CartRepository cartRepository, ProductService productService,UserRepository userRepository) {
		this.cartRepository = cartRepository;
		this.productService = productService;
		this.userRepository=userRepository;
	}

	@Transactional
	public String addProductToCart(int itemId,int userId) {
		Optional<Item> item = productService.getProduct(itemId);
		Optional<User> user = userRepository.findById(userId);
		if(item.isPresent() && user.isPresent())  {
			Cart cart=  new Cart();
			List<Item> items= new ArrayList<>();
			items.add(item.get());
			cart.setUser(user.get());
			cart.setPrice(item.get().getItemTotal());
			cart.setPurchasedDate(new Date());
			cart.setItems(items);
			cartRepository.save(cart);
		}
		return "Added Product to Cart";
	}

	public String deleteProductFromCart(int itemId, int cartId) {
		Optional<Item> item = productService.getProduct(itemId);
		Optional<Cart> cart = cartRepository.findById(cartId);
		if(item.isPresent() && cart.isPresent())  {
			cart.get().setPrice(cart.get().getPrice()-item.get().getItemTotal());
			item.get().setIsDeletedFromCart(true);
			List<Item> itemList= new ArrayList<>();
			itemList.add(item.get());
			cart.get().setItems(itemList);
			cartRepository.save(cart.get());
		}
		return "deleted SuccessFully";
	}

	public List<Cart> getAllProductsInCart() {
		return cartRepository.findbyIdentity();
	}

	public List<Cart> findByDateAndCategory(Date date,String category){
		return cartRepository.findByDateAndCategory(date,category);
	}
	public List<Cart> findByDate(Date date){
		return cartRepository.findByDate(date);
	}

	public List<Cart> findByCategory(String category) {
		
		return cartRepository.findByCategory(category); 
	}
	
}
