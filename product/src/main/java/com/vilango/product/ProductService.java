package com.vilango.product;

import jakarta.annotation.PostConstruct;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	private final ResourceLoader resourceLoader;

	private final JsonMapper jsonMapper;

	private List<ProductInfo> products = new ArrayList<>();

	public ProductService(ResourceLoader resourceLoader, JsonMapper jsonMapper) {
		this.resourceLoader = resourceLoader;
		this.jsonMapper = jsonMapper;
	}

	public double calculateTotalPrice(long productId, int quantity) {
		ProductInfo product = findProductById(productId);

		simulateWork();

		if (product == null) {
			throw new ProductApiException("Product not found");
		}

		return product.price() * quantity;
	}

	private void simulateWork() {
		try {
			Thread.sleep(250);
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Nullable private ProductInfo findProductById(long productId) {
		return products.stream().filter(p -> productId == p.productId()).findFirst().orElse(null);
	}

	@PostConstruct
	void setup() throws IOException {
		String PRODUCTS_JSON_PATH = "classpath:data/products.json";
		Resource resource = resourceLoader.getResource(PRODUCTS_JSON_PATH);

		if (!resource.exists()) {
			log.error("Products file not found at: {}", PRODUCTS_JSON_PATH);
			return;
		}

		try {
			this.products = jsonMapper.readValue(resource.getInputStream(), new TypeReference<>() {
			});
		}
		catch (IOException e) {
			log.error("Unexpected error loading product data", e);
			throw e;
		}

		products.forEach(p -> log.info("loaded product {}", p));
	}

}