package com.ecom.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entity.Order;



@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
	List<Order> findByUserName(String userName);
}
