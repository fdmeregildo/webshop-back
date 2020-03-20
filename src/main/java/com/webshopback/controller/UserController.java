package com.webshopback.controller;

import com.webshopback.config.SwaggerConfig;
import com.webshopback.config.exceptions.NotFoundException;
import com.webshopback.model.dto.UserDto;
import com.webshopback.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fmeregildo.
 */

@RestController
@RequestMapping(UserController.BASE_MAPPING)
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	static final String BASE_MAPPING = "/user";
	static final String MAPPING_GET_BY_USERNAME = "/{username}";
	static final String MAPPING_POST_USER_GUEST = "/guest";

	@Autowired
	private UserService userService;

	@GetMapping(MAPPING_GET_BY_USERNAME)
	@ApiOperation(value = "Get cart item for to add product", notes = "Get cart item for to add product from details product")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<UserDto> getUser(
		@ApiParam(value = "Username") @PathVariable(value = "username") String username) {

		UserDto userDto = userService.getUserByUsername(username);

		if (userDto == null) {
			throw new NotFoundException("Not Found User with Id " + username);
		}

		return ResponseEntity.ok(userDto);
	}

	@PostMapping(MAPPING_POST_USER_GUEST)
	@ApiOperation(value = "Get cart item for to add product", notes = "Get cart item for to add product from details product")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<UserDto> getAccessTokenUser() {

		UserDto userDto = userService.getUserGuest();

		return ResponseEntity.ok(userDto);
	}

}
