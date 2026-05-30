package com.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.entity.User;

@Service
public interface UserService {
	User saveUser(User user);

	User logiUser(String email, String password);

	List<User> getallUsers();

	User getUserByid(Long id);

	void deleteUser(Long id);

	User updateUser(User user);

	User findByEmail(String email);
}
