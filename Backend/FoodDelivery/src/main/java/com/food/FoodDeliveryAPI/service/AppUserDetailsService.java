package com.food.FoodDeliveryAPI.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.food.FoodDeliveryAPI.entity.UserEntity;
import com.food.FoodDeliveryAPI.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{
	private final UserRepository userRepository;

	public AppUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException("User not found"));
		return new User(user.getEmail(),user.getPassword(),Collections.emptyList());
	}
	
}
