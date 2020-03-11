package com.webshopback.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fmeregildo.
 */
@RestController
@RequestMapping(CheckoutController.BASE_MAPPING)
public class CheckoutController {

	final static String BASE_MAPPING = "api/checkout";

}
