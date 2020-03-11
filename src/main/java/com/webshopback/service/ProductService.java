package com.webshopback.service;

import com.webshopback.model.dto.ProductDto;
import java.math.BigInteger;

/**
 * Created by fmeregildo.
 */
public interface ProductService {

	public ProductDto getProductById(BigInteger id);
}
