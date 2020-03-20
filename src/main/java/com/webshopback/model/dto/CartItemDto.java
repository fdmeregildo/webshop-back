package com.webshopback.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by fmeregildo.
 */

@Getter
@Setter
public class CartItemDto implements Serializable {

	private ProductDto product;
	private int quantity;
	private BigDecimal priceTotal;


}
