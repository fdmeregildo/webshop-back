package com.webshopback.service;

import com.webshopback.model.dto.CustomerDto;
import org.springframework.stereotype.Service;

/**
 * Created by fmeregildo.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto getCustomerByUsername(String username){

		/**
		 * TODO
		 *
		 * Get information customer about database
		 */
		CustomerDto customer = new CustomerDto();
		customer.setUsername(username);
		customer.setEmail("userlxxxldigital@gmail.com");

		return customer;
	}

	@Override
	public CustomerDto getGuestCustomer(){

		/**
		 * TODO
		 *
		 * Implement Customer Guest Random
		 */

		CustomerDto customer = new CustomerDto();
		customer.setUsername("userguest");

		return customer;
	}
}
