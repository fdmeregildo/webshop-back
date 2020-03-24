package com.webshopback.service;

import com.webshopback.model.dto.AuthenticationDto;
import com.webshopback.util.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AuthenticationDto authenticationDto = null;

		if(Constants.USER_USERNAME.equals(username)){
			/** TODO
			 *  Get Data From Repository
			 */
			authenticationDto = new AuthenticationDto(Constants.USER_USERNAME, Constants.USER_PASSWORD, Arrays.asList("ROLE_USER"));

		}else if(Constants.USER_GUEST_USERNAME.equals(username)){
			authenticationDto = new AuthenticationDto(Constants.USER_GUEST_USERNAME, Constants.USER_PASSWORD, new ArrayList<>());
		}

		if (authenticationDto != null) {

			List<GrantedAuthority> authorityList = new ArrayList<>();
			authenticationDto.getRoles().stream().forEach(i -> authorityList.add(new SimpleGrantedAuthority(i)));

			return new User(authenticationDto.getUsername(), authenticationDto.getPassword(), authorityList);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

	}

}