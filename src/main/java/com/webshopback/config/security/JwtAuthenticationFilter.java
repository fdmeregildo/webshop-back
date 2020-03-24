package com.webshopback.config.security;

/**
 * Created by fmeregildo on 23/03/2020.
 */

import com.webshopback.service.AuthenticationServiceImpl;
import com.webshopback.service.UserService;
import com.webshopback.util.Constants;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private AuthenticationServiceImpl authenticationServiceImpl;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws ServletException, IOException {

		final String path = request.getRequestURI();
		logger.info("JwtAuthenticationFilter.doFilterInternal IN -->"+path);

		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			try {

				jwtToken = requestTokenHeader.substring(7);
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);

			} catch (IllegalArgumentException e) {
				logger.error("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				logger.error("JWT Token has expired");
			}

			// Once we get the token validate it.
			if (SecurityContextHolder.getContext().getAuthentication() == null) {
				setContextAuthentication(username, jwtToken, request);
			}

			//put header response
			response.addHeader("Authorization", "Bearer " + jwtToken);

		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		chain.doFilter(request, response);
	}


	private void setContextAuthentication(String username,
										  String jwtToken,
										  HttpServletRequest request) {

		//Authentication User
		UserDetails userDetails = this.authenticationServiceImpl.loadUserByUsername(username);

		// if token is valid configure Spring Security to manually set authentication
		if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
			setContextAuthenticationUser(userDetails, request);
		}
	}

	private void setContextAuthenticationGuest(String username, String jwtToken) {

		if (jwtTokenUtil.validateToken(jwtToken, username)) {
			try {
				setContextAuthenticationUserGuest(username);
			} catch (Exception e) {
				logger.error("Token generate Error " + e);
			}
		}

	}

	private void setContextAuthenticationUser(UserDetails userDetails,
											  HttpServletRequest request) {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
			new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	}

	private void setContextAuthenticationUserGuest(String username) throws Exception {
		logger.info("setContextAuthenticationUserGuest");
		SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken(
			"key", username, AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS")));
	}
}