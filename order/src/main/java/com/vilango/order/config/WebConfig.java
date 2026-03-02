package com.vilango.order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.StandardApiVersionDeprecationHandler;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void configureApiVersioning(ApiVersionConfigurer configurer) {
		var deprecationHandler = new StandardApiVersionDeprecationHandler();
		deprecationHandler.configureVersion("1.0")
			.setDeprecationDate(ZonedDateTime.of(2026, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
			.setSunsetDate(ZonedDateTime.of(2026, 12, 31, 0, 0, 0, 0, ZoneOffset.UTC));

		configurer.detectSupportedVersions(true)
			.usePathSegment(1)
			.setVersionRequired(true)
			.setDeprecationHandler(deprecationHandler);
	}

}