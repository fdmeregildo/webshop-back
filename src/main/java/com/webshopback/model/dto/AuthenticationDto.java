package com.webshopback.model.dto;

import java.io.Serializable;
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
public class AuthenticationDto implements Serializable {

	private String username;
	private String password;
	private List<String> roles;

}
