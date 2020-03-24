package com.webshopback.model.dto;

import java.io.Serializable;
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
public class UserDto implements Serializable {

	private Integer id;
	private String username;
	private String password;
	private String email;
	private String phone;

}
