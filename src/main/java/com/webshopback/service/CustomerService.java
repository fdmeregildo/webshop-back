package com.webshopback.service;

import com.webshopback.model.dto.CustomerDto;

/**
 * Created by fmeregildo.
 */
public interface CustomerService {

	public CustomerDto getCustomerByUsername(String username);

	public CustomerDto getGuestCustomer();

}
