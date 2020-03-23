package com.webshopback.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class ProductDto implements Serializable{


	private Integer id;
	private String code;
	private String name;
	private String description;
	private BigDecimal price;

	
}
