package com.webshopback.service;

import com.webshopback.config.exceptions.NotFoundException;
import com.webshopback.model.dto.CartDto;
import com.webshopback.model.dto.ItemDto;
import com.webshopback.model.dto.ProductDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fmeregildo.
 */

@Service
public class ShopCartServiceImpl implements ShopCartService {

	@Autowired
	private ProductService productoService;


	//Temporal Static
	private final static BigDecimal IVA_SPAIN = new BigDecimal("0.16");

	/**
	 * If product exist so it'll add quantity and calculate new price item
	 */

	@Override
	public CartDto addItem(CartDto cart, Integer idProduct, int quantity) {

		boolean exist = false;

		if (cart == null || cart.getItemDtoList() == null) {

			cart = new CartDto();
			cart.setItemDtoList(new ArrayList<>());

		} else {

			for (ItemDto item : cart.getItemDtoList()) {
				if (item.getProduct().getId().equals(idProduct)) {
					exist = true;

					item.setQuantity(item.getQuantity() + quantity);
					item.setPriceTotal(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
					break;
				}
			}
		}

		if (!exist) {
			ItemDto addItem = new ItemDto();

			ProductDto product = productoService.getProductById(idProduct);
			addItem.setProduct(product);
			addItem.setQuantity(quantity);
			addItem.setPriceTotal(product.getPrice().multiply(BigDecimal.valueOf(quantity)));

			cart.getItemDtoList().add(addItem);
		}

		recalculateCart(cart);

		return cart;
	}

	@Override
	public CartDto updateItem(CartDto cart, Integer idProduct, int quantity) {

		boolean exist = false;

		if (cart == null || cart.getItemDtoList() == null) {

			cart = new CartDto();
			cart.setItemDtoList(new ArrayList<>());

		} else {

			for (ItemDto item : cart.getItemDtoList()) {
				if (item.getProduct().getId().equals(idProduct)) {
					exist = true;

					item.setQuantity(quantity);
					item.setPriceTotal(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
					break;
				}
			}
		}

		if (!exist) {
			throw new NotFoundException("Item Not Found with idProduct " + idProduct);
		}

		recalculateCart(cart);

		return cart;
	}

	@Override
	public CartDto deleteItem(CartDto cart, Integer idProduct) {

		boolean exist = false;

		for (ItemDto item : cart.getItemDtoList()) {
			if (item.getProduct().getId().equals(idProduct)) {
				exist = true;
				cart.getItemDtoList().remove(item);
				break;
			}
		}

		if (!exist) {
			throw new NotFoundException("Item Not Found with idProduct " + idProduct);
		}

		recalculateCart(cart);

		return cart;
	}


	private void recalculateCart(CartDto cart) {

		BigDecimal subtotal = BigDecimal.ZERO;
		for (ItemDto item : cart.getItemDtoList()) {
			subtotal = subtotal.add(item.getPriceTotal());
		}

		cart.setSubTotal(subtotal);
		cart.setTotal(cart.getSubTotal().add((cart.getSubTotal().multiply(IVA_SPAIN))));
	}

}
