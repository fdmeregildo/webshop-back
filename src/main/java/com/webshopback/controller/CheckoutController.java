package com.webshopback.controller;

import com.webshopback.config.SwaggerConfig;
import com.webshopback.model.dto.CartDto;
import com.webshopback.model.dto.ItemAddDto;
import com.webshopback.model.dto.UserDto;
import com.webshopback.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fmeregildo.
 */
@RestController
@RequestMapping(CheckoutController.BASE_MAPPING)
public class CheckoutController {

	final static String BASE_MAPPING = "api/checkout";

	private UserService userService;

	@Autowired
	public CheckoutController(UserService userService){
		this.userService = userService;
	}

	@PostMapping
	@ApiOperation(value = "Checkout Cart", notes = "Checkout Cart")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<CartDto> checkout(HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		UserDto user = (UserDto) session.getAttribute("user");
		CartDto cart = (CartDto) session.getAttribute("cart");

		return ResponseEntity.ok(cart);
	}
}
