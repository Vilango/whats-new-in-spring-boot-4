package com.vilango.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ProductExceptionHandler {

	@ExceptionHandler(ProductApiException.class)
	ProblemDetail handleProductApiException(ProductApiException ex) {
		ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
				Objects.requireNonNullElse(ex.getMessage(), "Unknown product error"));
		problem.setTitle("Product Not Found");
		return problem;
	}

}