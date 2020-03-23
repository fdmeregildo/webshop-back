package com.webshopback.service.converters;

import com.webshopback.model.dto.ProductDto;
import com.webshopback.model.entity.ProductEntity;

/**
 * Created by fmeregildo.
 */
public class ProductDtoToEntityConvert {

	public ProductEntity convertToEntity(ProductDto productDto){

		ProductEntity productEntity = new ProductEntity();
		productEntity.setId(productDto.getId());
		productEntity.setName(productDto.getName());
		productEntity.setDescription(productDto.getDescription());
		productEntity.setName(productDto.getName());
		productEntity.setPrice(productDto.getPrice());

		return productEntity;
	}


}
