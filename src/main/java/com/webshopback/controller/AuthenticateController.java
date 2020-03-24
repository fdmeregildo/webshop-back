package com.webshopback.controller;

import com.webshopback.config.swagger.SwaggerConfig;
import com.webshopback.model.dto.UserDto;
import com.webshopback.model.jwt.JwtRequest;
import com.webshopback.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fmeregildo.
 */

@RestController
@RequestMapping
public class AuthenticateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateController.class);

	static final String AUTHENTICATE_MAPPING = "/authenticate";
	static final String REGISTER_MAPPING = "/register";

	private UserService userService;

	@Autowired
	public AuthenticateController(UserService userService){
		this.userService = userService;
	}


	@PostMapping(AUTHENTICATE_MAPPING)
	@ApiOperation(value = "Authenticate User", notes = "Authenticate User")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<UserDto> authenticateUser(
		@ApiParam(value = "Authenticate") @RequestBody JwtRequest req, HttpServletResponse res) throws Exception {

		String token = userService.authenticateJwtToken(req.getUsername(), req.getPassword());
		res.addHeader("Authorization", "Bearer " + token);

		UserDto userDto = userService.getUserByUsername(req.getUsername());

		return ResponseEntity.ok(userDto);
	}


	@PostMapping(REGISTER_MAPPING)
	@ApiOperation(value = "Register User", notes = "Register User")
	@ApiResponses(value = {@ApiResponse(code = 200, message = SwaggerConfig.OK_MESSAGE),
		@ApiResponse(code = 400, message = SwaggerConfig.BAD_REQUEST_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerConfig.INTERNAL_SERVER_ERROR_MESSAGE)})
	public ResponseEntity<UserDto> registerUser(
		@ApiParam(value = "user") @RequestBody UserDto userDto, HttpServletResponse res) throws Exception {


		String token = userService.authenticateJwtToken(userDto.getUsername(), userDto.getPassword());
		res.addHeader("Authorization", "Bearer " + token);

		return ResponseEntity.ok(userDto);
	}

}
