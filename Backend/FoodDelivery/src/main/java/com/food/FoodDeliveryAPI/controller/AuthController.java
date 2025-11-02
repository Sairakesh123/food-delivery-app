package com.food.FoodDeliveryAPI.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.FoodDeliveryAPI.io.AuthenticationRequest;
import com.food.FoodDeliveryAPI.io.AuthenticationResponse;
import com.food.FoodDeliveryAPI.service.AppUserDetailsService;
import com.food.FoodDeliveryAPI.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final AppUserDetailsService userDetailsService;
	private final JwtUtil jwtUtil;
	
	public AuthController(AuthenticationManager authenticationManager, AppUserDetailsService userDetailsService,JwtUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
	}


	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody AuthenticationRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		final String jwtToken = jwtUtil.generateToken(userDetails);
		return new AuthenticationResponse(request.getEmail(),jwtToken);
	}
}
