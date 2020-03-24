package com.webshopback.model.jwt;

import java.io.Serializable;

/**
 * Created by fmeregildo on 23/03/2020.
 */

public class JwtResponse implements Serializable {

	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;

	}
}
