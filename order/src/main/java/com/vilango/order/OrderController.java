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
@RequestMapping("/api/orders")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody PlaceOrder placeOrder) {

        Order order = new Order(1L,1L,1, 1.0);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}