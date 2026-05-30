package com.ecom.controler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.entity.Product;
import com.ecom.service.ProductService;

@Controller

public class ProductControler {
	@Autowired
	ProductService productService;

	@GetMapping("/admin/addProduct")
	public String addProductPage() {
		return "add-product";
	}

	@PostMapping("/product/saveproducts")
	public String saveProduct(Product product, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

		String uploadPath = "C:\\ecom\\uploads\\products";

		File uploadDir = new File(uploadPath);

		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		String fileName = imageFile.getOriginalFilename();

		product.setImageName(fileName);

		File saveFile = new File(uploadDir, fileName);

		System.out.println("Saving to: " + saveFile.getAbsolutePath());

		imageFile.transferTo(saveFile);

		productService.saveProduct(product);

		return "redirect:/admin/viewProducts";
	}

	@GetMapping("/admin/viewProducts")
	public String viewProducts(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "view-products";
	}

	@GetMapping("/product/edit/{id}")
	public String editProduct(@PathVariable Long id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "update-product";
	}

	@PostMapping("/product/update")
	public String updateProduct(Product product) {
		productService.upadateProduct(product);
		return "redirect:/admin/viewProducts";
	}

	@GetMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return "redirect:/admin/viewProducts";
	}

	@GetMapping("/user/search")
	public String searchProduct(@RequestParam String keyword, Model model) {
		List<Product> product = productService.searchProducts(keyword);
		model.addAttribute("product", product);
		return "user-dashboard";
	}
}
