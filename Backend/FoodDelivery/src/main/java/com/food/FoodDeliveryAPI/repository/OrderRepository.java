package com.food.FoodDeliveryAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.food.FoodDeliveryAPI.entity.OrderEntity;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String>{
	List<OrderEntity> findByUserId(String userId);
	Optional<OrderEntity> findByrazorpayOrderId(String razorPayOrderId);
}
