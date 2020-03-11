package com.webshopback.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fmeregildo.
 */
@RestController
@RequestMapping(AuthenticationController.BASE_MAPPING)
public class AuthenticationController {

	final static String BASE_MAPPING = "api/authentication";

}
