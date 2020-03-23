package com.webshopback.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by fmeregildo.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto implements Serializable {


	private ProductDto product;
	private int quantity;
	private BigDecimal priceTotal;


}
