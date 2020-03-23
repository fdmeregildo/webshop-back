package com.webshopback.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by fmeregildo.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final String OK_MESSAGE = "Operation OK";
	public static final String CREATED_OK_MESSAGE = "Resource created OK";
	public static final String DELETED_OK_MESSAGE = "Resource deleted OK";
	public static final String BAD_REQUEST_MESSAGE = "Bad Request";
	public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error";
	public static final String NOT_FOUND_MESSAGE = "Not Found";
	public static final String NO_CONTENT_MESSAGE = "No content";
	public static final String USER_ID_SWAGGER_DESCRIPTION = "User Id";
	public static final String AUTHENTICATION_ERROR_MESSAGE = "Authentication Error";

	@Bean
	public Docket api(){

		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.webshopback.controller"))
			.paths(PathSelectors.any())
			.build();
	}
}
