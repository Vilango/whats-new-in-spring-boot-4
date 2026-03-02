package com.vilango.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/{version}/orders")
public class OrderController {

	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping(version = "1.0")
	public ResponseEntity<Order> placeOrderV1(@RequestBody PlaceOrder placeOrder) {
		log.info("Placing order V1{}", placeOrder);
		Order order = orderService.placeOrderV1(placeOrder);
		log.info("Created order V1{}", order);

		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}

	@PostMapping(version = "2.0")
	public ResponseEntity<Order> placeOrderV2(@RequestBody PlaceOrder placeOrder) {
		log.info("Placing order V2{}", placeOrder);
		Order order = orderService.placeOrderV2(placeOrder);
		log.info("Created order V2{}", order);

		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}

}