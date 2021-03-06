package com.webshopback.service.converters;

import com.webshopback.model.dto.ProductDto;
import com.webshopback.model.entity.ProductEntity;

/**
 * Created by fmeregildo.
 */
public class ProductEntityToDtoConvert {

	public ProductDto convertToDto(ProductEntity productEntity){

		ProductDto dto = new ProductDto();
		dto.setId(productEntity.getId());
		dto.setCode(productEntity.getCode());
		dto.setName(productEntity.getName());
		dto.setDescription(productEntity.getDescription());
		dto.setPrice(productEntity.getPrice());

		return dto;
	}
}
