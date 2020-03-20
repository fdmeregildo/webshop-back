package com.webshopback.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by fmeregildo.
 */

@Getter
@Setter
public class CartDto implements Serializable {

	private Integer id;
	private List<CartItemDto> itemDtoList;
	private BigDecimal subTotal;
	private BigDecimal total;
	private CustomerDto customerDto;

}
