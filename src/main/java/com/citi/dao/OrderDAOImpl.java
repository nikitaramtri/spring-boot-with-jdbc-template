package com.citi.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.citi.entity.Order;

@Component
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*
	public OrderDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	*/
	
	@Override
	public List<Order> getAllOrders() {
		List<Map<String,Object>> orderListFromDB = jdbcTemplate.queryForList(
				"SELECT * FROM ORDER_MASTER");
		List<Order> orders = new ArrayList<Order>();
		System.out.println("In "+orderListFromDB.size());
		for(Map<String, Object> row: orderListFromDB) {
			Order order = 
				new Order((Integer)row.get("id"), (String)row.get("title"), (Double)row.get("price"), 
						(Integer)row.get("quantity"));
			orders.add(order);
		}
		return orders;
	}
	@Override
	public Order getOrderById(int id) {
		Order order = jdbcTemplate.queryForObject(
				"SELECT * FROM ORDER_MASTER WHERE ID = ?", new Object[]{id}, (rs, rowNum) ->
	            new Order(
	                    rs.getInt("id"),
	                    rs.getString("title"),
	                    rs.getDouble("price"),
	                    rs.getInt("quantity")
	            ));
		return order;
	}
	@Override
	public void addOrder(Order order) {
		
		jdbcTemplate.update("INSERT INTO ORDER_MASTER(ID, TITLE, PRICE, QUANTITY) VALUES(?,?, ?,?)", 
				order.getId(), order.getTitle(), order.getPrice(), order.getQuantity());
		System.out.println("In add");
	}
	
	@Override
	public void editOrder(Order order) {
		
		jdbcTemplate.update("UPDATE ORDER_MASTER SET TITLE = ?, PRICE = ?, QUANTITY = ? WHERE ID =?", 
				 order.getTitle(), order.getPrice(), order.getQuantity(), order.getId());
		System.out.println("In update");
	}
	@Override
	public void deleteOrderById(int id) {
		jdbcTemplate.update("DELETE FROM ORDER_MASTER WHERE ID = ?", id);
		System.out.println("In delete");
	}
	@Override
	public void deleteOrders() {
		jdbcTemplate.update("DELETE FROM ORDER_MASTER");
		System.out.println("In delete");
	}
}
