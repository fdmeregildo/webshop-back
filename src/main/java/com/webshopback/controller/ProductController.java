package com.webshopback.controller;

import com.webshopback.config.swagger.SwaggerConfig;
import com.webshopback.model.dto.ProductDto;
import com.webshopback.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fmeregildo.
 */
@RestController
@RequestMapping(ProductController.BASE_MAPPING)
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	final static String BASE_MAPPING = "/api/product";
	final static String MAPPING_GET_BY_ID = "/{id}";

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	@ApiOperation(value = "Get All Products", notes = "Get All Products")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<List<ProductDto>> getProductAll() {

		LOGGER.debug("ProductController.getProductAll INI");

		List<ProductDto> productDtoList = productService.getProducts();

		LOGGER.debug("ProductController.getProductAll OUT > HttpStatus.OK" + HttpStatus.OK);

		return ResponseEntity.ok(productDtoList);
	}

	@GetMapping(MAPPING_GET_BY_ID)
	@ApiOperation(value = "Get Product By Id", notes = "Get Product By Id")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<ProductDto> getProductById(
		@ApiParam(value = "Id Product", required = true) @PathVariable(value = "id", required = true) Integer id) {

		LOGGER.debug("ProductController.getProductById INI > data {id} " + id);

		ProductDto productDto = productService.getProductById(id);

		LOGGER.debug("ProductController.getProductById OUT > HttpStatus.OK" + HttpStatus.OK);

		return ResponseEntity.ok(productDto);
	}

}
