package com.vilango.order;

import com.vilango.order.product.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderService.class);

	RestClient restClient;

	private final AtomicLong idGenerator = new AtomicLong(0);

	public OrderService(RestClient.Builder restClientBuilder) {
		this.restClient = restClientBuilder.baseUrl("http://localhost:8081").build();
	}

	public Order placeOrder(PlaceOrder placeOrder) {
		log.info("Placing order with productId {} and quantity {}", placeOrder.productId(), placeOrder.quantity());
		ProductResponse response = restClient.get()
			.uri("/api/products/{productId}?quantity={quantity}", placeOrder.productId(), placeOrder.quantity())
			.retrieve()
			.body(ProductResponse.class);

		return new Order(idGenerator.incrementAndGet(), placeOrder.productId(), placeOrder.quantity(),
				response.totalPrice());
	}

}