package com.food.FoodDeliveryAPI.service;

import com.food.FoodDeliveryAPI.io.CartRequest;
import com.food.FoodDeliveryAPI.io.CartResponse;

public interface CartService {
	
	 CartResponse addToCart(CartRequest request);
	 
	 CartResponse getCart();
	 
	 void clearCart();
	
	 CartResponse removeFromCart(CartRequest cartRequest);
}
