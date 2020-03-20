package com.webshopback.service;

import com.webshopback.model.dto.ProductDto;
import com.webshopback.model.entity.ProductEntity;
import com.webshopback.repository.ProductRepository;
import com.webshopback.service.converters.ProductEntityToDtoConvert;
import java.math.BigInteger;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by fmeregildo on 11/03/2020.
 */

@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

	private static final BigInteger CODE_PRODUCT_EXIST = BigInteger.ONE;
	private static final BigInteger CODE_PRODUCT_NO_EXIST = BigInteger.valueOf(1000);

	@TestConfiguration
	static class ContextConfiguration {

		@Bean
		public ProductEntity ProductEntity() {
			return new ProductEntity();
		}

		@Bean
		public ProductService ProductService() {
			return new ProductServiceImpl();
		}

	}

	@Autowired
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;

	@Before
	public void setUp() {

		// Mock dependencies behavior
		mockGPKRepositoryBehavior();
	}

	@Test
	public void getProductByIdReturnOk() {

		ProductDto dto = productService.getProductById(CODE_PRODUCT_EXIST);

		Assert.assertNotNull(dto);
	}

	@Test
	public void suggestionsHotelConverterToDto() {

		ProductEntity entity = new ProductEntity();
		entity.setId(BigInteger.ONE);
		entity.setName("Producto Testing");

		ProductEntityToDtoConvert converter = new ProductEntityToDtoConvert();
		ProductDto dto = converter.convertToDto(entity);

		Assert.assertNotNull(dto);
	}


	public void mockGPKRepositoryBehavior() {

		ProductEntity entity = new ProductEntity();
		entity.setId(BigInteger.ONE);
		entity.setName("Product 01 Test");

		Mockito.doReturn(Optional.of(entity)).when(productRepository).findById(CODE_PRODUCT_EXIST);
	}
}
