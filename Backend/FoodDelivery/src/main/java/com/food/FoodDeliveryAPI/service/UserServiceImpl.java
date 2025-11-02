package com.food.FoodDeliveryAPI.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.food.FoodDeliveryAPI.entity.UserEntity;
import com.food.FoodDeliveryAPI.io.UserRequest;
import com.food.FoodDeliveryAPI.io.UserResponse;
import com.food.FoodDeliveryAPI.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository ;
    private PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;
	  
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder,AuthenticationFacade authenticationFacade) {
	        this.userRepository = userRepository;
	        this.passwordEncoder=passwordEncoder;
	        this.authenticationFacade = authenticationFacade;
    }

	@Override
	public UserResponse registerUser(UserRequest request) {
		UserEntity newUser = convertToEntity(request);
		newUser = userRepository.save(newUser);
		return convertToResponse(newUser);
	}
	
	private UserEntity convertToEntity(UserRequest request) {
		return UserEntity.builder()
		.email(request.getEmail())
		.password(passwordEncoder.encode(request.getPassword()))
		.name(request.getName())
		.build();
	}
	
	private UserResponse convertToResponse(UserEntity registeredUser) {
		return UserResponse.builder()
		.id(registeredUser.getId())
		.name(registeredUser.getName())
		.email(registeredUser.getEmail())
		.build();
	}

	@Override
	public String findByUserId() {
		String loggedInUserEmail = authenticationFacade.getAuthentication().getName();
		UserEntity loggedInUser = userRepository.findByEmail(loggedInUserEmail).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return loggedInUser.getId();
	}
		
}
