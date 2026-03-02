package com.vilango.order.config;

import com.vilango.order.product.ProductHttpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices(group = "product", types = { ProductHttpService.class })
public class HttpServiceConfig {

	@Bean
	RestClientHttpServiceGroupConfigurer groupConfigurer() {
		return groups -> {
			groups.forEachClient((_, builder) -> builder.baseUrl("http://localhost:8081").build());
		};
	}

}