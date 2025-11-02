package com.food.FoodDeliveryAPI.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
	public class FoodResponse {
	    private String id;
	    private String name;
	    private String description;
	    private String imageUrl;
	    private String price;
	    private String category;

	    // 🔹 Private constructor to enforce use of Builder
	    private FoodResponse(Builder builder) {
	        this.id = builder.id;
	        this.name = builder.name;
	        this.description = builder.description;
	        this.imageUrl = builder.imageUrl;
	        this.price = builder.price;
	        this.category = builder.category;
	    }

	    // ✅ Static nested Builder class
	    public static class Builder {
	        private String id;
	        private String name;
	        private String description;
	        private String imageUrl;
	        private String price;
	        private String category;

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

	        public Builder imageUrl(String imageUrl) {
	            this.imageUrl = imageUrl;
	            return this;
	        }

	        public Builder price(String price) {
	            this.price = price;
	            return this;
	        }

	        public Builder category(String category) {
	            this.category = category;
	            return this;
	        }

	        // 🔹 Final build() method
	        public FoodResponse build() {
	            return new FoodResponse(this);
	        }
	    }

	    // ✅ Static method to access builder
	    public static Builder builder() {
	        return new Builder();
	    }

	    // Getters (you can also generate setters if needed)
	    public String getId() { return id; }
	    public String getName() { return name; }
	    public String getDescription() { return description; }
	    public String getImageUrl() { return imageUrl; }
	    public String getPrice() { return price; }
	    public String getCategory() { return category; }
	}

