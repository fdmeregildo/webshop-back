package com.webshopback.controller;

import com.webshopback.config.SwaggerConfig;
import com.webshopback.config.exceptions.NotFoundException;
import com.webshopback.model.dto.CartItemDto;
import com.webshopback.model.dto.ProductDto;
import com.webshopback.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fmeregildo.
 */
@RestController
@RequestMapping(ProductController.BASE_MAPPING)
public class ProductController {

	final static String BASE_MAPPING = "api/product";
	final static String MAPPING_GET_BY_ID = "api/product";

	@Autowired
	private ProductService productService;

	@GetMapping
	@ApiOperation(value = "Get Product By Id", notes = "Get Product By Id")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<ProductDto> getProductById(
		@ApiParam(value = "Id Product", required = true) @RequestParam(value = "id", required = true) BigInteger id) {

		ProductDto productDto = productService.getProductById(id);

		if(productDto == null){
			throw new NotFoundException("Not Found Product with Id "+id);
		}

		return ResponseEntity.ok(productDto);
	}

}
