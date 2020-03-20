package com.webshopback.service;

import com.webshopback.config.exceptions.NotFoundException;
import com.webshopback.model.dto.ProductDto;
import com.webshopback.model.entity.ProductEntity;
import com.webshopback.repository.ProductRepository;
import com.webshopback.service.converters.ProductEntityToDtoConvert;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fmeregildo.
 */
@Service
public class ProductServiceImpl implements ProductService {


	@Autowired
	private ProductRepository productRepository;


	/**
	 * Get Information about Product by Id
	 */
	@Override
	public ProductDto getProductById(Integer idProduct) {

		ProductDto productDto = null;

		ProductEntity productEntity = productRepository.findById(idProduct)
			.orElseThrow(() -> new NotFoundException("getGlobalParamComboById with id " + idProduct));

		if (productEntity == null) {
			throw new NotFoundException("Not Found Product with Id " + idProduct);
		}

		ProductEntityToDtoConvert convert = new ProductEntityToDtoConvert();
		productDto = convert.convertToDto(productEntity);

		return productDto;
	}

	@Override
	public List<ProductDto> getProducts() {

		List<ProductDto> productDtoList = new ArrayList<>();
		List<ProductEntity> productEntityList = productRepository.findAll();

		if (productEntityList != null && !productEntityList.isEmpty()) {
			ProductEntityToDtoConvert toDtoConvert = new ProductEntityToDtoConvert();
			productEntityList.stream().forEach(i -> productDtoList.add(toDtoConvert.convertToDto(i)));
		}else {
			throw new NotFoundException("Products Not Found");
		}

		return productDtoList;
	}


}
