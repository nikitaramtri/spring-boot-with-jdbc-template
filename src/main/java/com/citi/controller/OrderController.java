package com.citi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citi.entity.Item;
import com.citi.entity.Order;
import com.citi.service.OrderService;

@RestController
@RequestMapping("/myapp")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	public String getAuthToken() {
		String uid = new java.rmi.server.UID().toString(); //alpha numeric
		String authToken = uid.substring(0, 20);
		return authToken;
	}
	
	@GetMapping(value="/order/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Order getOrderById(@PathVariable("id")String orderId) {
		int id = Integer.parseInt(orderId);
		return orderService.getOrderById(id);
	}	
	

//	@GetMapping(value="/order", produces=MediaType.APPLICATION_JSON_VALUE)
//	public Order getOrderById(@RequestParam("id")String orderId,
//			@RequestHeader("auth-token")String authToken) {
//		//System.out.println("authToken = " + authToken);
//		int id = Integer.parseInt(orderId);
//		return orderService.getOrderById(id);
//	}

	
	@GetMapping(value="/order", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}
	
	@PostMapping(value="/order", consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Order> createNewOrder(@RequestBody Order order) {
		System.out.println(order.toString());
		orderService.addOrder(order);
		return orderService.getAllOrders();
	}
	
	@PutMapping(value="/order", consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Order> editOrder(@RequestBody Order order) {
		System.out.println(order.toString());
		orderService.editOrder(order);
		return orderService.getAllOrders();
	}
	
	@DeleteMapping(value="/order/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Order> deleteOrderById(@PathVariable("id")String orderId) {
		int id = Integer.parseInt(orderId);
		orderService.deleteOrderById(id);
		return orderService.getAllOrders();
	}	
	
	@DeleteMapping(value="/order", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Order> deleteOrders() {
		orderService.deleteOrders();
		return orderService.getAllOrders();
	}	
	
}









