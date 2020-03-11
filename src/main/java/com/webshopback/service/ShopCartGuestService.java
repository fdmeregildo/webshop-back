package com.webshopback.service;

import com.webshopback.model.dto.CartDto;
import com.webshopback.model.dto.CartItemDto;

/**
 * Created by fmeregildo.
 */


public interface ShopCartGuestService {

	public CartItemDto getCartItemData(CartItemDto item);

	public CartDto getCartData(CartDto cart);

}
