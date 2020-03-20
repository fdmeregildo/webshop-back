package com.webshopback.service;

import com.webshopback.model.dto.CartDto;
import com.webshopback.model.dto.CartItemDto;

/**
 * Created by fmeregildo.
 */


public interface ShopCartGuestService {

	public CartDto addItem(CartDto cart, Integer idProduct, int quantity);

	public CartDto deleteItem(CartDto cart, Integer idProduct);

}
