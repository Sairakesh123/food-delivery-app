package com.food.FoodDeliveryAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.food.FoodDeliveryAPI.entity.FoodEntity;

@Repository
public interface FoodRepository extends MongoRepository<FoodEntity, String>{

}
