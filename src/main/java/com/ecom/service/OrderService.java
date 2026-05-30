package com.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.entity.Order;



@Service
public interface OrderService {
	Order saveOrder(Order order);

	List<Order> getAllOrders();

	List<Order> getOrdersByUserName(String userName);
}
