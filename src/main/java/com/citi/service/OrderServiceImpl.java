package com.citi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.dao.OrderDAO;
import com.citi.entity.Order;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDao;
	
	@Override
	public List<Order> getAllOrders() {
		return orderDao.getAllOrders();
	}
	@Override
	public Order getOrderById(int id) {
		return orderDao.getOrderById(id);
	}
	@Override
	public void addOrder(Order order) {
		orderDao.addOrder(order);
	}
	
	@Override
	public void editOrder(Order order) {
		orderDao.editOrder(order);
	}
	@Override
	public void deleteOrderById(int id) {
		orderDao.deleteOrderById(id);
		
	}
	@Override
	public void deleteOrders() {
		orderDao.deleteOrders();
		
	}
	
}
