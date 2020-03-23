package com.webshopback.model.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by fmeregildo on 22/03/2020.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemAddDto implements Serializable{


	@NotNull
	private Integer idProduct;

	@NotNull
	private Integer quantity;

}
