package com.webshopback.model.dto;

import com.fasterxml.jackson.databind.node.BigIntegerNode;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
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
public class CustomerDto  extends  UserDto implements Serializable {

	private List<AddressDto> addressDtoList;

	/**
	 * To Complete with others attributes
	 */


}
