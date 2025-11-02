package com.food.FoodDeliveryAPI.io;

import java.util.List;

public class OrderResponse {
	private String id;
	private String userId;
	private String userAddress;
	private String phoneNumber;
	private String email;
	private double amount;
	private String paymentStatus;
	private String razorpayOrderId;
	private String orderStatus;
	private List<OrderItem> orderedItems;

	// ✅ No-Args Constructor (optional, useful for serialization)
	public OrderResponse() {
	}

	// ✅ Private constructor — used by Builder
	private OrderResponse(Builder builder) {
		this.id = builder.id;
		this.userId = builder.userId;
		this.userAddress = builder.userAddress;
		this.phoneNumber = builder.phoneNumber;
		this.email = builder.email;
		this.amount = builder.amount;
		this.paymentStatus = builder.paymentStatus;
		this.razorpayOrderId = builder.razorpayOrderId;
		this.orderStatus = builder.orderStatus;
		this.orderedItems = builder.orderedItems;
	}

	// ✅ Static builder() method (Lombok-style)
	public static Builder builder() {
		return new Builder();
	}

	// ✅ Static inner Builder class
	public static class Builder {
		private String id;
		private String userId;
		private String userAddress;
		private String phoneNumber;
		private String email;
		private double amount;
		private String paymentStatus;
		private String razorpayOrderId;
		private String orderStatus;
		public List<OrderItem> orderedItems;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder userId(String userId) {
			this.userId = userId;
			return this;
		}

		public Builder userAddress(String userAddress) {
			this.userAddress = userAddress;
			return this;
		}

		public Builder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder amount(double amount) {
			this.amount = amount;
			return this;
		}

		public Builder paymentStatus(String paymentStatus) {
			this.paymentStatus = paymentStatus;
			return this;
		}

		public Builder razorpayOrderId(String razorpayOrderId) {
			this.razorpayOrderId = razorpayOrderId;
			return this;
		}

		public Builder orderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
			return this;
		}
		
		public Builder orderedItems(List<OrderItem> orderedItems) {
			this.orderedItems = orderedItems;
			return this;
		}

		public OrderResponse build() {
			return new OrderResponse(this);
		}
	}

	// ✅ Getters
	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public double getAmount() {
		return amount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public String getrazorpayOrderId() {
		return razorpayOrderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public List<OrderItem> getOrderedItems() {
		return orderedItems;
	}

	// ✅ Optional toString for debugging
	@Override
	public String toString() {
		return "OrderResponse{" + "id='" + id + '\'' + ", userId='" + userId + '\'' + ", userAddress='" + userAddress
				+ '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", email='" + email + '\'' + ", amount=" + amount
				+ ", paymentStatus='" + paymentStatus + '\'' + ", razorpayOrderId='" + razorpayOrderId + '\''
				+ ", orderStatus='" + orderStatus + '\'' + '}' + ", orderedItems='"+ orderedItems + '\'' +'}';
	}
}
