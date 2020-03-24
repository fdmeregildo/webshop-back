package com.webshopback.service;

import com.webshopback.model.dto.UserDto;
import org.apache.tomcat.jni.User;

/**
 * Created by fmeregildo.
 */
public interface UserService {

	String authenticateJwtToken(String username, String password) throws Exception;

	String authenticateGuestJwtToken() throws Exception;

	UserDto getUserByUsername(String username);

	UserDto saveUser(UserDto userDto);
}
