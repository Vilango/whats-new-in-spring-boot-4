package com.vilango.order.config;

import com.vilango.order.product.ProductHttpService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices(group = "product", types = { ProductHttpService.class })
public class HttpServiceConfig {

}