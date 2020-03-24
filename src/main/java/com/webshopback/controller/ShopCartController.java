package com.webshopback.controller;

import com.webshopback.config.swagger.SwaggerConfig;
import com.webshopback.model.dto.CartDto;
import com.webshopback.model.dto.ItemAddDto;
import com.webshopback.model.dto.ItemDto;
import com.webshopback.model.dto.ProductDto;
import com.webshopback.service.ShopCartService;
import com.webshopback.service.UserService;
import com.webshopback.util.Constants;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fmeregildo.
 */

@RestController
@RequestMapping(ShopCartController.BASE_MAPPING)
public class ShopCartController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopCartController.class);

	final static String BASE_MAPPING = "/api/cart";
	final static String CART_ITEM_MAPPING = "/item";
	final static String CART_MAPPING_CURRENT = "/current";

	@Autowired
	private ShopCartService shopCartService;

	@Autowired
	private UserService userService;

	@Autowired
	public ShopCartController(ShopCartService shopCartService, UserService userService) {

		this.shopCartService = shopCartService;
		this.userService = userService;
	}


	@GetMapping
	@ApiOperation(value = "Get information Cart", notes = "Get information cart from general cart button")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<CartDto> getCart(HttpServletRequest req) throws Exception {

		LOGGER.debug("ShopCartController.getCart INI");

		HttpSession session = req.getSession(true);
		CartDto cart = (CartDto) session.getAttribute("cart");

		return ResponseEntity.ok(cart);
	}


	@PostMapping(CART_ITEM_MAPPING)
	@ApiOperation(value = "Post item", notes = "Post item for add product")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<CartDto> addItem(
		@ApiParam(value = "Item to Add", required = true) @Valid @RequestBody(required = true) ItemAddDto item,
		HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		CartDto cart = (CartDto) session.getAttribute("cart");

		cart = shopCartService.addItem(cart, item.getIdProduct(), item.getQuantity());
		session.setAttribute("cart", cart);

		return ResponseEntity.ok(cart);
	}

	@PutMapping(CART_ITEM_MAPPING)
	@ApiOperation(value = "Put item", notes = "Put item for update product")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<CartDto> updateItem(
		@ApiParam(value = "Item to Update", required = true) @Valid @RequestBody(required = true) ItemAddDto item,
		HttpServletRequest req) {

		HttpSession session = req.getSession(true);
		CartDto cart = (CartDto) session.getAttribute("cart");

		cart = shopCartService.updateItem(cart, item.getIdProduct(), item.getQuantity());
		session.setAttribute("cart", cart);

		return ResponseEntity.ok(cart);
	}

	@DeleteMapping(CART_ITEM_MAPPING)
	@ApiOperation(value = "Delete item", notes = "Delete item for to delete product")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<CartDto> deleteItem(
		@ApiParam(value = "Id Product", required = true) @RequestParam(value = "idProduct", required = true) Integer idProduct,
		HttpServletRequest req) {

		HttpSession session = req.getSession(true);
		CartDto cart = (CartDto) session.getAttribute("cart");

		cart = shopCartService.deleteItem(cart, idProduct);

		return ResponseEntity.ok(cart);
	}

}
