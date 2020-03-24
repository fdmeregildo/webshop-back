package com.webshopback.service;

import com.webshopback.model.dto.CartDto;

/**
 * Created by fmeregildo.
 */


public interface ShopCartService {

	public CartDto addItem(CartDto cart, Integer idProduct, int quantity);

	public CartDto updateItem(CartDto cart, Integer idProduct, int quantity);

	public CartDto deleteItem(CartDto cart, Integer idProduct);

}
