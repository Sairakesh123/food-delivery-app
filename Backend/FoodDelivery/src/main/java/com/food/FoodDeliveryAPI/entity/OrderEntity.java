package com.food.FoodDeliveryAPI.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.food.FoodDeliveryAPI.io.OrderItem;

@Document(collection = "orders")
public class OrderEntity {

	@Id
	private String id;
	private String userId;
	private String userAddress;
	private String phoneNumber;
	private String email;
	private List<OrderItem> orderedItems;
	private double amount;
	private String paymentStatus;
	private String razorpayOrderId;
	private String razorPaySignature;
	private String razorpayPaymentId;
	private String orderStatus;

	// ✅ No-Args Constructor
	public OrderEntity() {
	}

	// ✅ All-Args Constructor (same as @AllArgsConstructor)
	public OrderEntity(String id, String userId, String userAddress, String phoneNumber, String email,
			List<OrderItem> orderedItems, double amount, String paymentStatus, String razorpayOrderId,
			String razorPaySignature,String razorpayPaymentId, String orderStatus) {
		this.id = id;
		this.userId = userId;
		this.userAddress = userAddress;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.orderedItems = orderedItems;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.razorpayOrderId = razorpayOrderId;
		this.razorPaySignature = razorPaySignature;
		this.razorpayPaymentId = razorpayPaymentId;
		this.orderStatus = orderStatus;
	}

	// ✅ Private constructor for Builder
	private OrderEntity(Builder builder) {
		this.id = builder.id;
		this.userId = builder.userId;
		this.userAddress = builder.userAddress;
		this.phoneNumber = builder.phoneNumber;
		this.email = builder.email;
		this.orderedItems = builder.orderedItems;
		this.amount = builder.amount;
		this.paymentStatus = builder.paymentStatus;
		this.razorpayOrderId = builder.razorpayOrderId;
		this.razorPaySignature = builder.razorPaySignature;
		this.razorpayPaymentId = builder.razorpayPaymentId;
		this.orderStatus = builder.orderStatus;
	}

	// ✅ Static builder() method (like Lombok's @Builder)
	public static Builder builder() {
		return new Builder();
	}

	// ✅ Inner Builder class
	public static class Builder {
		private String id;
		private String userId;
		private String userAddress;
		private String phoneNumber;
		private String email;
		private List<OrderItem> orderedItems;
		private double amount;
		private String paymentStatus;
		private String razorpayOrderId;
		private String razorPaySignature;
		private String razorpayPaymentId;
		private String orderStatus;

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

		public Builder orderedItems(List<OrderItem> orderedItems) {
			this.orderedItems = orderedItems;
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

		public Builder razorPaySignature(String razorPaySignature) {
			this.razorPaySignature = razorPaySignature;
			return this;
		}
		
		public Builder razorpayPaymentId(String razorpayPaymentId) {
			this.razorpayPaymentId=razorpayPaymentId;
			return this;
		}

		public Builder orderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
			return this;
		}

		public OrderEntity build() {
			return new OrderEntity(this);
		}
	}

	// ✅ Getters and Setters
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

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<OrderItem> getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(List<OrderItem> orderedItems) {
		this.orderedItems = orderedItems;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getrazorpayOrderId() {
		return razorpayOrderId;
	}

	public void setrazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}

	public String getRazorPaySignature() {
		return razorPaySignature;
	}

	public void setRazorPaySignature(String razorPaySignature) {
		this.razorPaySignature = razorPaySignature;
	}
	
	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}

	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	// ✅ toString() for logging/debugging
	@Override
	public String toString() {
		return "OrderEntity{" + "id='" + id + '\'' + ", userId='" + userId + '\'' + ", userAddress='" + userAddress
				+ '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", email='" + email + '\'' + ", orderedItems="
				+ orderedItems + ", amount=" + amount + ", paymentStatus='" + paymentStatus + '\''
				+ ", razorpayOrderId='" + razorpayOrderId + '\'' + ", razorPaySignature='" + razorPaySignature + '\''
				+", razorpayPaymentId='"+ razorpayPaymentId +'\''+ ", orderStatus='" + orderStatus + '\'' + '}';
	}
}
