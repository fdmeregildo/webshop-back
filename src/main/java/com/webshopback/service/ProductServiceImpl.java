package com.webshopback.service;

import com.webshopback.model.dto.ProductDto;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

/**
 * Created by fmeregildo.
 */
 @Service
public class ProductServiceImpl implements  ProductService{

	/**
	 * Get Information about Product by Id
	 * @param id
	 * @return
	 */
	public ProductDto getProductById(BigInteger id){

		ProductDto product = new ProductDto();
		product.setIdProduct(id);
		product.setName("Product Id "+id);
		product.setDescription("Short description about Producto with id "+id);
		product.setPrice(BigDecimal.valueOf(RandomUtils.nextInt(100, 1000)));

		return product;
	}
}
