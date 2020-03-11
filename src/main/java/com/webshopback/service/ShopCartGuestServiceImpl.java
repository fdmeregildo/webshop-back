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
	public CartItemDto getCartItemData(CartItemDto item) {

		ProductDto product = productoService.getProductById(item.getProduct().getIdProduct());

		if (product == null) {
			throw new NotFoundException("Not Found Product with Id " + item.getProduct().getIdProduct());
		}

		item.setProduct(product);
		item.setQuantity(
			(item.getQuantity() != null) ? item.getQuantity() + BigInteger.ONE.intValue() : BigInteger.ONE.intValue());
		item.setPriceTotal(item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())));

		return item;
	}

	@Override
	public CartDto getCartData(CartDto cart) {

		//Assign temporal data
		cart.setCustomerDto(customerService.getGuestCustomer());
		cart.setSubTotal(calculateSubTotalItems(cart.getItemDtoList()));
		cart.setTotal(cart.getSubTotal().multiply(IVA_SPAIN));

		return cart;
	}

	private BigDecimal calculateSubTotalItems(List<CartItemDto> items) {

		BigDecimal subtotal = BigDecimal.ZERO;
		for (CartItemDto item : items) {
			subtotal = subtotal.add(item.getPriceTotal());
		}

		return subtotal;
	}

}
