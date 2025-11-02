package com.food.FoodDeliveryAPI.io;

public class OrderItem {
    private String foodId;
    private int quantity;
    private double price;
    private String category;
    private String imageUrl;
    private String description;
    private String name;

    // ✅ No-args constructor
    public OrderItem() {}

    // ✅ All-args constructor
    public OrderItem(String foodId, int quantity, double price, String category, String imageUrl, String description, String name) {
        this.foodId = foodId;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
        this.description = description;
        this.name = name;
    }

    // ✅ Getters and Setters
    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ✅ toString() method
    @Override
    public String toString() {
        return "OrderItem{" +
                "foodId='" + foodId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    // ✅ Manual Builder pattern
    public static class Builder {
        private String foodId;
        private int quantity;
        private double price;
        private String category;
        private String imageUrl;
        private String description;
        private String name;

        public Builder foodId(String foodId) {
            this.foodId = foodId;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
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

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(foodId, quantity, price, category, imageUrl, description, name);
        }
    }
}

