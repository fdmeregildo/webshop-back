package com.webshopback.config.security;

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

/**
 * Created by fmeregildo .
 */
@Component
public class JwtGuestFilter extends OncePerRequestFilter {

	@Autowired
	private AuthenticationServiceImpl authenticationServiceImpl;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws ServletException, IOException {

		final String path = request.getRequestURI();
		final String method = request.getMethod();
		logger.info("JwtGuestFilter.doFilterInternal IN -->"+path+" - "+method);


		if ("/api/cart/item".equals(path) && method.equals("POST")) {

			final String requestTokenHeader = request.getHeader("Authorization");
			if (!(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer "))) {

				try {
					//Generate Token Guest User
					String jwtToken = userService.authenticateGuestJwtToken();
					setContextAuthentication(Constants.USER_GUEST_USERNAME, jwtToken, request);

					//put header response
					response.addHeader("Authorization", "Bearer " + jwtToken);

				} catch (Exception ex) {
					logger.error("Token generate Error "+ex);
				}
			}
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

	private void setContextAuthenticationUser(UserDetails userDetails,
											  HttpServletRequest request) {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
			new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	}

}