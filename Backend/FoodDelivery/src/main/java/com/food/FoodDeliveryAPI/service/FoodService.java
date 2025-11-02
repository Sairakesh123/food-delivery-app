package com.food.FoodDeliveryAPI.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.food.FoodDeliveryAPI.io.FoodRequest;
import com.food.FoodDeliveryAPI.io.FoodResponse;

public interface FoodService {
	String uploadFile(MultipartFile file);
	
	FoodResponse addFood(FoodRequest request,MultipartFile file);
	
	List<FoodResponse> readFoods();
	
	FoodResponse readFood(String id);
	
	boolean deleteFile(String filename);
	
	void deleteFood(String id);
}
