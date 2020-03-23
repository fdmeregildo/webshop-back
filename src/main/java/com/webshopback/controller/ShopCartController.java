package com.webshopback.controller;

import com.webshopback.config.SwaggerConfig;
import com.webshopback.model.dto.CartDto;
import com.webshopback.model.dto.ItemAddDto;
import com.webshopback.service.ShopCartGuestService;
import com.webshopback.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fmeregildo.
 */

@RestController
@RequestMapping(ShopCartController.BASE_MAPPING)
public class ShopCartController {

	final static String BASE_MAPPING = "/api/cart";
	final static String CART_ITEM_MAPPING = "/item";
	final static String CART_MAPPING_CURRENT = "/current";

	private ShopCartGuestService shopCartGuestService;
	private UserService userService;

	@Autowired
	public ShopCartController(ShopCartGuestService shopCartGuestService, UserService userService){

		this.shopCartGuestService = shopCartGuestService;
		this.userService = userService;
	}


	@GetMapping
	@ApiOperation(value = "Get information Cart", notes = "Get information cart from general cart button")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<CartDto> getCart(HttpServletRequest req) {

		HttpSession session = req.getSession(true);
		CartDto cart = (CartDto) session.getAttribute("cart");

		return ResponseEntity.ok(cart);
	}


	@PostMapping(CART_ITEM_MAPPING)
	@ApiOperation(value = "Post cart item", notes = "Post cart item for add product")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<CartDto> addItem(
		@ApiParam(value = "Item to Add", required = true) @Valid @RequestBody(required = true) ItemAddDto item,
		HttpServletRequest req) {

		HttpSession session = req.getSession(true);
		CartDto cart = (CartDto) session.getAttribute("cart");

		cart = shopCartGuestService.addItem(cart, item.getIdProduct(), item.getQuantity());
		session.setAttribute("cart", cart);

		return ResponseEntity.ok(cart);
	}

	@PutMapping(CART_ITEM_MAPPING)
	@ApiOperation(value = "Put cart item", notes = "Put cart item for update product")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<CartDto> updateItem(
		@ApiParam(value = "Item to Update", required = true) @Valid @RequestBody(required = true) ItemAddDto item,
		HttpServletRequest req) {

		HttpSession session = req.getSession(true);
		CartDto cart = (CartDto) session.getAttribute("cart");

		cart = shopCartGuestService.updateItem(cart, item.getIdProduct(), item.getQuantity());
		session.setAttribute("cart", cart);

		return ResponseEntity.ok(cart);
	}

	@DeleteMapping(CART_ITEM_MAPPING)
	@ApiOperation(value = "Delete cart item", notes = "Delete cart item for to delete product")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<CartDto> deleteItem(
		@ApiParam(value = "Id Product", required = true) @RequestParam(value = "idProduct", required = true) Integer idProduct,
		HttpServletRequest req) {

		HttpSession session = req.getSession(true);
		CartDto cart = (CartDto) session.getAttribute("cart");

		cart = shopCartGuestService.deleteItem(cart, idProduct);

		return ResponseEntity.ok(cart);
	}

}
