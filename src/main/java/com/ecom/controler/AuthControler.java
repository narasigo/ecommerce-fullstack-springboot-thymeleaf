package com.ecom.controler;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecom.entity.User;
import com.ecom.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthControler {
	@Autowired
	private UserService userService;

	@GetMapping("/")

	public String homePage() {
		return "index";
	}

	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}

	@PostMapping("/saveUser")
	public String saveUser(User user, Model model) {
		User saveUser = userService.saveUser(user);
		if (saveUser != null) {
			model.addAttribute("sucess", "registarion sucessfull");
		} else {
			model.addAttribute("error", "registration failed");
		}
		return "register";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@PostMapping("/loginUser")
	public String loginUser(String email, String password,
	                        HttpSession session,
	                        RedirectAttributes redirectAttributes) {

	    User user = userService.logiUser(email, password);

	    if (user != null) {

	        session.setAttribute("user", user);

	        if (user.getRole().equals("ADMIN")) {
	            return "redirect:/admin/dashboard";
	        } else {
	            return "redirect:/user/dashboard";
	        }
	    }

	    redirectAttributes.addFlashAttribute("error",
	            "Invalid email or password");

	    return "redirect:/login";
	}

	@GetMapping("/logoutUser")
	public String logoutUser(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
