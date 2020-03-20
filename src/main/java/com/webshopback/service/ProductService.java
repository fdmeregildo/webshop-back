package com.webshopback.service;

import com.webshopback.model.dto.ProductDto;
import java.util.List;

/**
 * Created by fmeregildo.
 */
public interface ProductService {

	ProductDto getProductById(Integer id);

	List<ProductDto> getProducts();
}
