package com.webshopback.controller;

import com.webshopback.config.swagger.SwaggerConfig;
import com.webshopback.model.dto.CartDto;
import com.webshopback.model.dto.UserDto;
import com.webshopback.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fmeregildo.
 */
@RestController
@RequestMapping(CheckoutController.BASE_MAPPING)
public class CheckoutController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutController.class);

	final static String BASE_MAPPING = "/api/checkout";

	private UserService userService;

	@Autowired
	public CheckoutController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	@ApiOperation(value = "Checkout Cart", notes = "Checkout Cart")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<?> checkout(HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails) auth.getPrincipal();

		LOGGER.info("Session User -->"+user.getUsername());

		CartDto cart = (CartDto) session.getAttribute("cart");

		/** TODO
		 *  Procesar Orden de compra de productos
		 */

		return ResponseEntity.ok(user);
	}
}
