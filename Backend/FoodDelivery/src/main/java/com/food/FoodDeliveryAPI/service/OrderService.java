package com.food.FoodDeliveryAPI.service;

import java.util.List;
import java.util.Map;

import com.food.FoodDeliveryAPI.io.OrderRequest;
import com.food.FoodDeliveryAPI.io.OrderResponse;
import com.razorpay.RazorpayException;

public interface OrderService {
	OrderResponse createOrderWithPayment(OrderRequest request) throws RazorpayException;
	
	void verifyPayment(Map<String, String> paymentData,String status);
	
	List<OrderResponse> getUserOrders(); 
	
	void removeOrder(String OrderId);
	
	List<OrderResponse> getOrdersOfAllUsers();
	
	void updateOrderStatus(String orderId,String status);
}
