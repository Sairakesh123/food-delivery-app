package com.food.FoodDeliveryAPI.io;

public class CartRequest {

    private String foodId;
    
    // Default constructor
    public CartRequest() {
    }

    // All-args constructor
    public CartRequest(String foodId) {
        this.foodId = foodId;
    }

    // Getter
    public String getFoodId() {
        return foodId;
    }

    // Setter
    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    // toString()
    @Override
    public String toString() {
        return "CartRequest{" +
                "foodId='" + foodId + '\'' +
                '}';
    }

    // -------- Manual Builder --------
    public static class Builder {
        private String foodId;

        public Builder foodId(String foodId) {
            this.foodId = foodId;
            return this;
        }

        public CartRequest build() {
            return new CartRequest(foodId);
        }
    }

    // Static method to start builder
    public static Builder builder() {
        return new Builder();
    }
}
