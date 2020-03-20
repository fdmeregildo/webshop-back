package com.webshopback.service;

import com.webshopback.model.dto.UserDto;

/**
 * Created by fmeregildo.
 */
public interface UserService {

	public UserDto getUserByUsername(String username);

	public UserDto getUserGuest();
}
