package com.ecom.controler;

import com.ecom.service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.entity.Order;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
public class OrderControler {
	@Autowired
	private OrderService orderService;

	OrderControler(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/user/placeOrder")
	public String placeOrder(@RequestParam String productName, @RequestParam double price, @RequestParam int quantity,
			HttpSession session) {
		String userName = (String) session.getAttribute("userName");
		Order order = new Order();
		order.setUserName(userName);
		order.setProductName(productName);
		order.setPrice(price);
		order.setQuantity(quantity);
		orderService.saveOrder(order);
		return "redirect:/user/order";
	}

	@GetMapping("/user/order")
	public String userOrder(HttpSession session, Model model) {
		String userName = (String) session.getAttribute("userName");
		System.out.println("UserName from session = " + userName);
		List<Order> order = orderService.getOrdersByUserName(userName);
		model.addAttribute("orders", order);
		return "user-orders";
	}

	@GetMapping("/admin/order")
	public String adminOrder(Model model) {
		List<Order> order = orderService.getAllOrders();
		model.addAttribute("order", order);
		return "view-orders";
	}
}
