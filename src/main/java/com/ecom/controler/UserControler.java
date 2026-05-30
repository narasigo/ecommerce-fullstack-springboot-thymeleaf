package com.ecom.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.entity.Product;
import com.ecom.entity.User;
import com.ecom.service.ProductService;
import com.ecom.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller

public class UserControler {
	@Autowired

	private UserService userService;
	@Autowired
private ProductService productService;
	@GetMapping("/user/dashboard")
	public String dashboard() {
		return "user-dashboard";
	}
	 

	@GetMapping("/user/profile")
	public String profile(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedinUser");
		
		
		 if (user == null) {
		 
		 return "redirect:/login"; }
		 System.out.println("PROFILE USER = " + user);
		model.addAttribute("user", user);
		return "profile";
	}
	

	@GetMapping("/edit/{id}")
	public String editUser(@PathVariable Long id, Model model) {
		User user = userService.getUserByid(id);
		model.addAttribute("user", user);
		return "update-user";
	}

	@PostMapping("/user/updateUser")
	public String updateUser(User user, HttpSession session, Model model) {
		User updatUser = userService.updateUser(user);
		session.setAttribute("loggedinUser", updatUser);
		model.addAttribute("Sucess", "profileupdatedsucessfully");
		model.addAttribute("user", updatUser);
		return "profile";
	}
	@GetMapping("/user/viewProducts")
	public String viewProducts(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "view-products-user";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id, HttpSession session) {
		userService.deleteUser(id);
		session.invalidate();
		return "redirect:/register";
	}
	@GetMapping("/forgot-password")
	public String forgotPasswordPage() {
	    return "forgot-password";
	}
	@PostMapping("/verify-email")
	public String verifyEmail(@RequestParam String email, Model model, HttpSession session) {

	    User user = userService.findByEmail(email);

	    if (user != null) {

	        session.setAttribute("resetUser", user);

	        return "redirect:/reset-password";

	    } else {
	        model.addAttribute("error", "Email not found");
	        return "forgot-password";
	    }
	}
	@GetMapping("/reset-password")
	public String resetPasswordPage() {
	    return "reset-password";
	}
	@PostMapping("/update-password")
	public String updatePassword(@RequestParam String newPassword,
	                             HttpSession session) {

	    User user = (User) session.getAttribute("resetUser");

	    if (user != null) {

	        user.setPassword(newPassword);
	        userService.saveUser(user); // or update method

	        session.removeAttribute("resetUser");

	        return "redirect:/login";
	    }

	    return "redirect:/forgot-password";
	}
}
