package com.webshopback.service;

import com.webshopback.config.exceptions.NotFoundException;
import com.webshopback.model.dto.CartDto;
import com.webshopback.model.dto.CartItemDto;
import com.webshopback.model.dto.ProductDto;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fmeregildo.
 */

@Service
public class ShopCartGuestServiceImpl implements ShopCartGuestService {

	@Autowired
	private ProductService productoService;

	@Autowired
	private CustomerService customerService;

	//Temporal Static
	private final static BigDecimal IVA_SPAIN = new BigDecimal("0.16");

	/**
	 * If product exist so it'll add quantity and calculate new price item
	 */

	@Override
	public CartDto addItem(CartDto cart, Integer idProduct, int quantity) {

		boolean exist = false;

		for (CartItemDto item: cart.getItemDtoList()) {
			if(item.getProduct().getId().equals(idProduct)){
				exist = true;

				item.setQuantity(item.getQuantity() + quantity);
				item.setPriceTotal(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
				break;
			}
		}

		if(!exist){
			CartItemDto addItem = new CartItemDto();

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
	public CartDto deleteItem(CartDto cart, Integer idProduct) {

		boolean exist = false;

		for (CartItemDto item : cart.getItemDtoList()) {
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
		for (CartItemDto item : cart.getItemDtoList()) {
			subtotal = subtotal.add(item.getPriceTotal());
		}

		cart.setCustomerDto(customerService.getGuestCustomer());
		cart.setSubTotal(subtotal);
		cart.setTotal(cart.getSubTotal().add((cart.getSubTotal().multiply(IVA_SPAIN))));
	}

}
