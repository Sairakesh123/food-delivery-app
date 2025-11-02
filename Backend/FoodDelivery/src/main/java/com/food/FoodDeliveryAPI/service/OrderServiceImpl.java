package com.food.FoodDeliveryAPI.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.food.FoodDeliveryAPI.entity.OrderEntity;
import com.food.FoodDeliveryAPI.io.OrderItem;
import com.food.FoodDeliveryAPI.io.OrderRequest;
import com.food.FoodDeliveryAPI.io.OrderResponse;
import com.food.FoodDeliveryAPI.repository.CartRepository;
import com.food.FoodDeliveryAPI.repository.FoodRepository;
import com.food.FoodDeliveryAPI.repository.OrderRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private final OrderRepository orderRepository;

	@Autowired
	private final CartRepository cartRepository;

	@Autowired
	private final UserService userService;

	@Autowired
	private FoodRepository foodRepository;

	@Value("${razorpay_key}")
	private String RAZORPAY_KEY;

	@Value("${razorpay_secret}")
	private String RAZORPAY_SECRET;

	public OrderServiceImpl(OrderRepository orderRepository, UserService userService, CartRepository cartRepository) {
		super();
		this.orderRepository = orderRepository;
		this.cartRepository = null;
		this.userService = userService;
	}

//	@Override
//	public OrderResponse createOrderWithPayment(OrderRequest request) throws RazorpayException {
//	    // ✅ Validate all foodIds before proceeding
//	    request.getOrderedItems().forEach(item -> {
//	        boolean exists = foodRepository.existsById(item.getFoodId());
//	        if (!exists) {
//	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "foodId not found: " + item.getFoodId());
//	        }
//	    });
//
//	    // ✅ Then proceed to create the order
//	    OrderEntity newOrder = convertToEntity(request);
//	    newOrder = orderRepository.save(newOrder);
//
//	    RazorpayClient razorpayClient = new RazorpayClient(RAZORPAY_KEY, RAZORPAY_SECRET);
//	    JSONObject orderRequest = new JSONObject();
//	    orderRequest.put("amount", newOrder.getAmount()*100);
//	    orderRequest.put("currency", "INR");
//	    orderRequest.put("payment_capture", 1);
//
//	    Order razorpayOrder = razorpayClient.orders.create(orderRequest);
//	    newOrder.setrazorpayOrderId(razorpayOrder.get("id"));
//
//	    String loggedInUserId = userService.findByUserId();
//	    newOrder.setUserId(loggedInUserId);
//
//	    newOrder = orderRepository.save(newOrder);
//	    return convertToResponse(newOrder);
//	}

	@Override
	public OrderResponse createOrderWithPayment(OrderRequest request) throws RazorpayException {
		System.out.println("✅ Received OrderRequest: " + request);

		// 1️⃣ Validate all food IDs
		for (OrderItem item : request.getOrderedItems()) {
			System.out.println("🔍 Checking Food ID: " + item.getFoodId());
			if (item.getFoodId() == null || item.getFoodId().isEmpty()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Food ID must not be null or empty.");
			}
			boolean exists = foodRepository.existsById(item.getFoodId());
			System.out.println("✅ Exists in DB? " + exists);
			if (!exists) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Food ID not found: " + item.getFoodId());
			}
		}

		try {
			// 2️⃣ Fetch logged-in userId from JWT (Spring Security context)
			String loggedInUserId = null;
			try {
				loggedInUserId = userService.findByUserId();
				System.out.println("👤 Logged-in user ID: " + loggedInUserId);
			} catch (Exception e) {
				System.out.println("⚠️ Unable to fetch logged-in userId, proceeding with request data...");
			}

			// 3️⃣ Create Razorpay order
			RazorpayClient razorpayClient = new RazorpayClient(RAZORPAY_KEY, RAZORPAY_SECRET);
			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", request.getAmount() * 100); // convert to paise
			orderRequest.put("currency", "INR");
			orderRequest.put("payment_capture", 1);

			Order razorpayOrder = razorpayClient.orders.create(orderRequest);
			System.out.println("💰 Razorpay order created: " + razorpayOrder);

			// 4️⃣ Save order in MongoDB
			OrderEntity orderEntity = OrderEntity.builder()
					.userId(loggedInUserId != null ? loggedInUserId : request.getUserId())
					.userAddress(request.getUserAddress()).phoneNumber(request.getPhoneNumber())
					.email(request.getEmail()).orderedItems(request.getOrderedItems()).amount(request.getAmount())
					.paymentStatus("created").razorpayOrderId(razorpayOrder.get("id")).orderStatus("Preparing").build();

			orderRepository.save(orderEntity);
			System.out.println("🗃️ Order saved in MongoDB with ID: " + orderEntity.getId());

			// 5️⃣ Build and return response
			OrderResponse response = OrderResponse.builder().id(orderEntity.getId()).userId(orderEntity.getUserId())
					.userAddress(orderEntity.getUserAddress()).phoneNumber(orderEntity.getPhoneNumber())
					.email(orderEntity.getEmail()).amount(orderEntity.getAmount())
					.paymentStatus(orderEntity.getPaymentStatus()).razorpayOrderId(orderEntity.getrazorpayOrderId())
					.orderStatus(orderEntity.getOrderStatus()).orderedItems(orderEntity.getOrderedItems()).build();

			System.out.println("✅ Returning OrderResponse to frontend: " + response);
			return response;

		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error while creating Razorpay order: " + e.getMessage());
		}
	}

	private OrderResponse convertToResponse(OrderEntity newOrder) {
		return OrderResponse.builder().id(newOrder.getId()).amount(newOrder.getAmount())
				.userAddress(newOrder.getUserAddress()).userId(newOrder.getUserId())
				.razorpayOrderId(newOrder.getrazorpayOrderId()).paymentStatus(newOrder.getPaymentStatus())
				.orderStatus(newOrder.getOrderStatus()).email(newOrder.getEmail())
				.phoneNumber(newOrder.getPhoneNumber()).orderedItems(newOrder.getOrderedItems()).build();
	}

	private OrderEntity convertToEntity(OrderRequest request) {
		return OrderEntity.builder().userAddress(request.getUserAddress()).amount(request.getAmount())
				.orderedItems(request.getOrderedItems()).email(request.getEmail()).phoneNumber(request.getPhoneNumber())
				.orderStatus(request.getOrderStatus()).build();

	}

	@Override
	public void verifyPayment(Map<String, String> paymentData, String status) {
		String razorpayOrderId = paymentData.get("razorpay_order_id");
		OrderEntity existingOrder = orderRepository.findByrazorpayOrderId(razorpayOrderId)
				.orElseThrow(() -> new RuntimeException("Order not found"));
		existingOrder.setPaymentStatus(status);
		existingOrder.setRazorPaySignature(paymentData.get("razorpay_signature"));
		existingOrder.setRazorpayPaymentId(paymentData.get("razorpay_payment_id"));
		orderRepository.save(existingOrder);
		if ("paid".equalsIgnoreCase(status)) {
			cartRepository.deleteByUserId(existingOrder.getUserId());
		}
	}

	@Override
	public List<OrderResponse> getUserOrders() {
		String loggedInUserId = userService.findByUserId();
		List<OrderEntity> list = orderRepository.findByUserId(loggedInUserId);
		return list.stream().map(entity -> convertToResponse(entity)).collect(Collectors.toList());
	}

	@Override
	public void removeOrder(String OrderId) {
		orderRepository.deleteById(OrderId);
	}

	@Override
	public List<OrderResponse> getOrdersOfAllUsers() {
		List<OrderEntity> list = orderRepository.findAll();
		return list.stream().map(entity -> convertToResponse(entity)).collect(Collectors.toList());
	}

	@Override
	public void updateOrderStatus(String orderId, String status) {
		OrderEntity entity = orderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order not found"));
		entity.setOrderStatus(status);
		orderRepository.save(entity);

	}

}
