package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entity.Cart;
import com.ecom.services.CartService;

@RestController
@RequestMapping("/cart")
@PreAuthorize("hasRole('USER')")
public class CartController {

	private CartService cartService;
	
	@Autowired
	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}

	@PostMapping("/{itemId}/{userId}")
	public String addProductToCart(@PathVariable(value = "itemId")int itemId,@PathVariable(value = "userId")int userId) {
		return cartService.addProductToCart(itemId, userId);
	}
	
	@DeleteMapping("/{itemId}")
	public String removeProductFromCart(@PathVariable(value = "cartId")int cartId,@PathVariable(value = "itemId")int itemId) {
		return cartService.deleteProductFromCart(itemId,cartId);
	}
	@GetMapping
	public List<Cart> getAllProductsInCart(){
		return cartService.getAllProductsInCart();
	}
	
}
