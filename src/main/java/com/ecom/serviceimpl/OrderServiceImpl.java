package com.ecom.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entity.Order;
import com.ecom.repo.OrderRepo;
import com.ecom.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepo orderRepo;

	@Override
	public Order saveOrder(Order order) {
		try {
			return orderRepo.save(order);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Order> getAllOrders() {
		try {
			return orderRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Order> getOrdersByUserName(String userName) {
		try {
			return orderRepo.findByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
