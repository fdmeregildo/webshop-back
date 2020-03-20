package com.webshopback.controller;

import com.webshopback.config.SwaggerConfig;
import com.webshopback.model.dto.CartDto;
import com.webshopback.model.dto.CartItemDto;
import com.webshopback.service.ShopCartGuestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	final static String CART_MAPPING_CURRENT = "/current";
	final static String CART_MAPPING_ITEM = "/item";

	private ShopCartGuestService shopCartGuestService;

	@Autowired
	public ShopCartController(ShopCartGuestService shopCartGuestService){
		this.shopCartGuestService = shopCartGuestService;
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


	@PostMapping(CART_MAPPING_ITEM)
	@ApiOperation(value = "Post cart item", notes = "Post cart item for to add product")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<CartDto> addItem(
		@ApiParam(value = "Id Product", required = true) @RequestParam(value = "idProduct", required = true) Integer idProduct,
		@ApiParam(value = "Quantity", required = true) @RequestParam(value = "quantity", required = true) Integer quantity,
		HttpServletRequest req) {

		HttpSession session = req.getSession(true);
		CartDto cart = (CartDto) session.getAttribute("cart");

		cart = shopCartGuestService.addItem(cart, idProduct, quantity);
		session.setAttribute("cart", cart);

		return ResponseEntity.ok(cart);
	}


	@DeleteMapping(CART_MAPPING_ITEM)
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
