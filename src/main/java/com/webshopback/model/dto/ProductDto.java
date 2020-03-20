package com.webshopback.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by fmeregildo.
 */

@Getter
@Setter
public class ProductDto implements Serializable{


	private Integer id;
	private String name;
	private String description;
	private BigDecimal price;

	
}
