package com.food.FoodDeliveryAPI.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "foods")
public class FoodEntity {
	    @Id
	    private String id;
	    private String name;
	    private String description;
	    private double price;
	    private String category;
	    private String imageUrl;
	    
	    

	    public FoodEntity() {
			super();
		}

		public FoodEntity(String id, String name, String description, double price, String category, String imageUrl) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.price = price;
			this.category = category;
			this.imageUrl = imageUrl;
		}

		// 🔹 Private constructor for Builder
	    private FoodEntity(Builder builder) {
	        this.id = builder.id;
	        this.name = builder.name;
	        this.description = builder.description;
	        this.price = builder.price;
	        this.category = builder.category;
	        this.imageUrl = builder.imageUrl;
	    }

	    // ✅ Static nested Builder class
	    public static class Builder {
	        private String id;
	        private String name;
	        private String description;
	        private double price;
	        private String category;
	        private String imageUrl;

	        public Builder id(String id) {
	            this.id = id;
	            return this;
	        }

	        public Builder name(String name) {
	            this.name = name;
	            return this;
	        }

	        public Builder description(String description) {
	            this.description = description;
	            return this;
	        }

	        public Builder price(double price) {
	            this.price = price;
	            return this;
	        }

	        public Builder category(String category) {
	            this.category = category;
	            return this;
	        }

	        public Builder imageUrl(String imageUrl) {
	            this.imageUrl = imageUrl;
	            return this;
	        }

	        // 🔹 Final build() method
	        public FoodEntity build() {
	            return new FoodEntity(this);
	        }
	    }

	    // ✅ Static method to start building
	    public static Builder builder() {
	        return new Builder();
	    }

	    // 🔹 Getters and Setters
	    public String getId() { return id; }
	    public void setId(String id) { this.id = id; }

	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }

	    public String getDescription() { return description; }
	    public void setDescription(String description) { this.description = description; }

	    public double getPrice() { return price; }
	    public void setPrice(double price) { this.price = price; }

	    public String getCategory() { return category; }
	    public void setCategory(String category) { this.category = category; }

	    public String getImageUrl() { return imageUrl; }
	    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
	}