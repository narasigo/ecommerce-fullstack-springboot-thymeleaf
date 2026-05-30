package com.ecom.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.entity.User;
import com.ecom.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminControler {
	@Autowired
	UserService userService;

	@GetMapping("/admin/dashboard")

	public String adminDashboard() {

		return "admin-dashboard";
	}

	@GetMapping("/admin/addUser")
	public String addUserPage() {
		return "add-user";
	}

	@GetMapping("/admin/viewUsers")
	public String viewUsers(Model model) {

		List<User> users = userService.getallUsers();

		model.addAttribute("users", users);

		return "view-users";
	}

	/*
	 * @GetMapping("/admin/addProduct") public String addProductpage() { return
	 * "add-product"; }
	 */
	/*
	 * @GetMapping("/admin/viewProducts") public String viewProductsPage() { return
	 * "view-products"; }
	 */

	@GetMapping("/admin/editUser/{id}")
	public String editUser(

			@PathVariable Long id,

			Model model) {

		User user = userService.getUserByid(id);

		model.addAttribute("user", user);

		return "update-user";
	}
	@GetMapping("/admin/delete/{id}")
	public String deleteUser(@PathVariable Long id, HttpSession session) {
		userService.deleteUser(id);
		session.invalidate();
		 return "redirect:/admin/viewUsers";
	}
}
