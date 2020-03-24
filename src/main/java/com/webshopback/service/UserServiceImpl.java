package com.webshopback.service;

import com.webshopback.config.security.JwtTokenUtil;
import com.webshopback.model.dto.UserDto;
import com.webshopback.util.Constants;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Created by fmeregildo.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthenticationServiceImpl userDetailsService;


	@Override
	public String authenticateJwtToken(String username, String password) throws Exception {

		authenticate(username, password);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		String token = jwtTokenUtil.generateToken(userDetails);

		return token;
	}

	@Override
	public String authenticateGuestJwtToken() throws Exception{

		Map<String, Object> claims = new HashMap<>();
		String token = jwtTokenUtil.doGenerateToken(claims, Constants.USER_GUEST_USERNAME);

		return token;
	}

	@Override
	public UserDto getUserByUsername(String username) {

		/** TODO
		 *  Get Data From Repository
		 */
		UserDto userDto = new UserDto(123, Constants.USER_USERNAME, "password", "emailtest", "phonetest");

		return userDto;
	}

	@Override
	public UserDto saveUser(UserDto userDto) {

		/** TODO
		 *  Register Data From Repository
		 */

		userDto = new UserDto(123, Constants.USER_USERNAME, "password", "emailtest", "phonetest");

		return userDto;
	}


	private void authenticate(String username, String password) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

	}
}
