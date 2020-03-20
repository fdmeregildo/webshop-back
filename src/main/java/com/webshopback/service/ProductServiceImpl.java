package com.webshopback.service;

import com.webshopback.config.exceptions.NotFoundException;
import com.webshopback.model.dto.ProductDto;
import com.webshopback.model.entity.ProductEntity;
import com.webshopback.repository.ProductRepository;
import com.webshopback.service.converters.ProductEntityToDtoConvert;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fmeregildo.
 */
 @Service
public class ProductServiceImpl implements  ProductService{


 	@Autowired
	private ProductRepository productRepository;


	/**
	 * Get Information about Product by Id
	 * @param id
	 * @return
	 */
	public ProductDto getProductById(Integer id){

		ProductDto productDto = null;

		ProductEntity productEntity = productRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("getGlobalParamComboById with id " + id));

		ProductEntityToDtoConvert convert = new ProductEntityToDtoConvert();
		productDto = convert.convertToDto(productEntity);

		return productDto;
	}
}
