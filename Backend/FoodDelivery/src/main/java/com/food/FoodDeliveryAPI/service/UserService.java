package com.food.FoodDeliveryAPI.service;


import com.food.FoodDeliveryAPI.io.UserRequest;
import com.food.FoodDeliveryAPI.io.UserResponse;

public interface UserService {
	
	UserResponse registerUser(UserRequest request);
	
	String findByUserId();
}
