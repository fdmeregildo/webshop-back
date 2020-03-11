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
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	final static String CART_MAPPING_RESOURCE = "/item";

	@Autowired
	private ShopCartGuestService shopCartGuestService;

	@GetMapping
	@ApiOperation(value = "Get information Cart", notes = "Get information Cart from Cart Button")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<CartDto> getCart(
		@ApiParam(value = "Cart Data", required = true) @Valid @RequestBody(required = true) CartDto cartDto) {

		CartDto cartDtoResponse = new CartDto();
		List<CartItemDto> itemsDtoListReponse = new ArrayList<>();

		for (CartItemDto item : cartDto.getItemDtoList()) {
			itemsDtoListReponse.add(shopCartGuestService.getCartItemData(item));
		}

		cartDtoResponse.setItemDtoList(itemsDtoListReponse);
		cartDtoResponse = shopCartGuestService.getCartData(cartDto);

		return ResponseEntity.ok(cartDtoResponse);
	}


	@GetMapping(CART_MAPPING_RESOURCE)
	@ApiOperation(value = "Get cart item for to add product", notes = "Get cart item for to add product from details product")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<CartItemDto> getCartItem(
		@ApiParam(value = "Cart Item Data", required = true) @RequestBody(required = true) CartItemDto cartItemDto) {

		CartItemDto itemsDtoReponse = shopCartGuestService.getCartItemData(cartItemDto);

		return ResponseEntity.ok(itemsDtoReponse);
	}


}
