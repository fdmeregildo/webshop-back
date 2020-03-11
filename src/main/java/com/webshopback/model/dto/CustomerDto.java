package com.webshopback.model.dto;

import com.fasterxml.jackson.databind.node.BigIntegerNode;
import java.io.Serializable;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by fmeregildo.
 */

@Getter
@Setter
public class CustomerDto implements Serializable {

	private BigInteger id;
	private String username;
	private String email;

}
