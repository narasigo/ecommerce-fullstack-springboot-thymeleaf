package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entity.Product;
import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
List<Product> findByProductNameContaining(String keyword);
}
