package com.food.FoodDeliveryAPI.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.food.FoodDeliveryAPI.io.OrderRequest;
import com.food.FoodDeliveryAPI.io.OrderResponse;
import com.food.FoodDeliveryAPI.service.OrderService;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public OrderResponse createOrderWithPayment(@RequestBody OrderRequest request) throws RazorpayException{
		OrderResponse response = orderService.createOrderWithPayment(request);
		return response;
	}
	
	@PostMapping("/verify")
	public void verifyPayment(@RequestBody Map<String, String> paymentData) {
		orderService.verifyPayment(paymentData, "paid");
	}
	
	@GetMapping
	public List<OrderResponse> getOrders(){
		return orderService.getUserOrders();
	}
	
	@DeleteMapping("/{orderId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable String orderId) {
		orderService.removeOrder(orderId);
	}
	
	
	//admin panel
	@GetMapping("/all")
	public List<OrderResponse> getOrdersOfAllUsers(){
		return orderService.getOrdersOfAllUsers();
	}
	
	//admin panel
	@PatchMapping("/status/{orderId}")
	public void updateOrderStatus(@PathVariable String orderId,@RequestParam String status) {
		orderService.updateOrderStatus(orderId, status);
	}

}
