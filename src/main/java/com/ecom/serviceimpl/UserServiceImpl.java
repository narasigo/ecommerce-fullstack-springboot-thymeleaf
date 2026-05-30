package com.ecom.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.entity.User;
import com.ecom.repo.UserRepository;
import com.ecom.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) {
		try {

			String encodedpassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedpassword);
			return userRepository.save(user);
		} catch (Exception e) {
			System.out.println("error while saving user");
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public List<User> getallUsers() {
		try {
			return userRepository.findAll();
		} catch (Exception e) {
			System.out.println("error while getting all user");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserByid(Long id) {
		try {
			return userRepository.findById(id).orElse(null);
		} catch (Exception e) {
			System.out.println("error while gettimg user by id");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteUser(Long id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			System.out.println("error while deleting user");
			e.printStackTrace();
		}

	}

	@Override
	public User updateUser(User user) {
		try {
			String encodedpassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedpassword);
			return userRepository.save(user);

		} catch (Exception e) {
			System.out.println("error while updating user");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User logiUser(String email, String password) {
		try {
			User user = userRepository.findByEmail(email);
			if (user != null) {
				boolean isPasswordMatched = passwordEncoder.matches(password, user.getPassword());

				if (isPasswordMatched) {
					return user;
				}
			}
		} catch (Exception e) {
			System.out.println("error while login user");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findByEmail(String email) {
		try {
			return userRepository.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		
		return null;
	}
	}
}
