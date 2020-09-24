package com.citi.service;

import java.util.List;

import com.citi.entity.Order;

public interface OrderService {
	List<Order> getAllOrders();

	Order getOrderById(int id);

	void addOrder(Order order);

	void editOrder(Order order);

	void deleteOrderById(int id);

	void deleteOrders();
	
}
