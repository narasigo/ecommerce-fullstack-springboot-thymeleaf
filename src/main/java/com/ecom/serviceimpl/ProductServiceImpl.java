package com.ecom.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entity.Product;
import com.ecom.repo.ProductRepo;
import com.ecom.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepo productRepo;

	@Override
	public Product saveProduct(Product product) {
		try {
			return productRepo.save(product);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Product> getAllProducts() {
		try {
			return productRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Product getProductById(Long id) {
		try {
			return productRepo.findById(id).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Product upadateProduct(Product product) {
		try {
			return productRepo.save(product);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteProduct(Long id) {
		try {
			productRepo.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> searchProducts(String keyword) {
		try {
			return productRepo.findByProductNameContaining(keyword);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
