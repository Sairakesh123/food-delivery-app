package com.food.FoodDeliveryAPI.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.food.FoodDeliveryAPI.io.CartRequest;
import com.food.FoodDeliveryAPI.io.CartResponse;
import com.food.FoodDeliveryAPI.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	private final CartService cartService;

	// Constructor injection (recommended)
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@PostMapping
	public CartResponse addToCart(@RequestBody CartRequest request) {
		String foodId = request.getFoodId();
		if (foodId == null || foodId.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"foodId not found");
		}
		return cartService.addToCart(request);
	}
	
	@GetMapping
	public CartResponse getCart() {
		return cartService.getCart();
	}
	
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void clearCart() {
		cartService.clearCart();
	}
	
	@PostMapping("/remove")
	public CartResponse removeFromCart(@RequestBody CartRequest request) {
		String foodId = request.getFoodId();
		if (foodId == null || foodId.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"foodId not found");
		}
		return cartService.removeFromCart(request);
	}
}

