package com.vilango.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> calculatePricing(@PathVariable long productId, @RequestParam int quantity) {
		log.info("calculating price for product with id {} and quantity {}", productId, quantity);

		double totalPrice = productService.calculateTotalPrice(productId, quantity);
		Product pricing = new Product(totalPrice);

		return new ResponseEntity<>(pricing, HttpStatus.OK);
	}

}