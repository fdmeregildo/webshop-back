package com.webshopback.controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by fmeregildo on
 * .
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ProductControllerIntegrationTest {

	public static BigInteger ID_PRODUCT_EXIST = BigInteger.ONE;


	@Test
	public void getProductByIdShouldReturnOk() {

		JsonPath apiResponse =

			RestAssured
				.given()
				.when()
				.get(ProductController.BASE_MAPPING + ProductController.MAPPING_GET_BY_ID, ID_PRODUCT_EXIST)
				.then()
				.statusCode(HttpStatus.OK.value())
				.extract().jsonPath();

		Assert.assertNotNull(apiResponse.get());

	}
}
