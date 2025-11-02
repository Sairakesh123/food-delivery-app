package com.food.FoodDeliveryAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.food.FoodDeliveryAPI.io.UserRequest;
import com.food.FoodDeliveryAPI.io.UserResponse;
import com.food.FoodDeliveryAPI.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {
	

    private final UserService userService;
 

    public UserController(UserService userService) {
        this.userService = userService;
    }
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse register(@RequestBody UserRequest request) {
		return userService.registerUser(request);
		
	}
}
