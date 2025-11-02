package com.food.FoodDeliveryAPI.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "carts")
public class CartEntity {

	@Id
    private String id;
    private String userId;
    private Map<String, Integer> items = new HashMap<>();
    
    // Default constructor
    public CartEntity() {
    }

    // All-args constructor
    public CartEntity(String id, String userId, Map<String, Integer> items) {
        this.id = id;
        this.userId = userId;
        this.items = items;
    }

    // Custom constructor (without id)
    public CartEntity(String userId, Map<String, Integer> items) {
        this.userId = userId;
        this.items = items;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    // toString()
    @Override
    public String toString() {
        return "CartEntity{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", items=" + items +
                '}';
    }

    // Manual Builder class
    public static class Builder {
        private String id;
        private String userId;
        private Map<String, Integer> items = new HashMap<>();

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder items(Map<String, Integer> items) {
            this.items = items;
            return this;
        }

        public CartEntity build() {
            return new CartEntity(id, userId, items);
        }
    }

    // Static builder method
    public static Builder builder() {
        return new Builder();
    }
}
