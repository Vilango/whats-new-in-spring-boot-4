package com.vilango.order.product;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface ProductHttpService {

	@GetExchange("/api/products/{productId}?quantity={quantity}")
	ProductResponse getTotalPrice(@PathVariable long productId, @PathVariable int quantity);

}