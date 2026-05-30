package com.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.entity.Product;

@Service
public interface ProductService {
	Product saveProduct(Product product);

	List<Product> getAllProducts();

	Product getProductById(Long id);

	Product upadateProduct(Product product);

	void deleteProduct(Long id);

	List<Product> searchProducts(String keyword);
}
