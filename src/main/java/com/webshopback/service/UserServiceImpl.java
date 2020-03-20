package com.webshopback.service;

import com.fasterxml.jackson.databind.node.BigIntegerNode;
import com.webshopback.model.dto.UserDto;
import java.math.BigInteger;
import liquibase.database.core.Ingres9Database;
import org.springframework.stereotype.Service;

/**
 * Created by fmeregildo.
 */

@Service
public class UserServiceImpl implements  UserService {

	//DATA TEMP
	private final static BigInteger USER_ID = BigInteger.valueOf(123123);

	private final static String USER_EMAIL = "userregistered@gmaillcom";
	@Override
	public UserDto getUserByUsername(String username){

		/**
		 * TODO
		 * Get User from repository
		 */

		UserDto userDto = new UserDto();
		userDto.setId(USER_ID);
		userDto.setUsername(username);
		userDto.setEmail(USER_EMAIL);

		return userDto;
	}

	@Override
	public UserDto getUserGuest() {
		return null;
	}


}
