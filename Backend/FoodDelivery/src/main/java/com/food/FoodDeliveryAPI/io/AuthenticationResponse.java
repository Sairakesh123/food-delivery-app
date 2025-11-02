package com.food.FoodDeliveryAPI.io;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
	private String email;
	private String token;
	public String getEmail() {
		return email;
	}
	public String getToken() {
		return token;
	}
	public AuthenticationResponse(String email, String token) {
		super();
		this.email = email;
		this.token = token;
	}
	
	
	
}
