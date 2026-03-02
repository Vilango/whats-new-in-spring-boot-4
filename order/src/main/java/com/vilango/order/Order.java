package com.vilango.order;

public record Order(long id, long productId, int quantity, Double totalPrice) {
}
