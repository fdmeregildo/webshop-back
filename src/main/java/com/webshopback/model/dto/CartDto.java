package com.webshopback.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;
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
public class CartDto implements Serializable {

	private Integer id;

	@NotNull
	private List<ItemDto> itemDtoList;

	private BigDecimal subTotal;
	private BigDecimal total;


}
