package com.webshopback.config.security;


import com.webshopback.service.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by fmeregildo.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private WebAuthenticationException webAuthenticationException;

	@Autowired
	private AuthenticationServiceImpl userDetailsService;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private JwtGuestFilter jwtGuestFilter;


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		// We don't need CSRF
		httpSecurity.csrf().disable()

			// dont authenticate this particular request
			.authorizeRequests()

			//Authentication
			.antMatchers(HttpMethod.POST, "/authenticate").permitAll()

			//ShopCar
			.antMatchers(HttpMethod.POST, "/api/cart/item").permitAll()

			//View Products
			.antMatchers(HttpMethod.GET, "/api/product/**").permitAll()

			//Checkout
			.antMatchers("/api/checkout").hasAuthority("ROLE_USER")

			// all other requests need to be authenticated
			.anyRequest().authenticated().and()

			// make sure we use stateless session; session won't be used to
			// store user's state.
			.exceptionHandling().authenticationEntryPoint(webAuthenticationException).and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(jwtGuestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
