package com.cashback.api.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author georgi.yolovski
 *
 */
@RestController
@PreAuthorize("!isAnonymous()")
@RequestMapping("/api/test")
public class TestController {
	
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String getMessage(){
		return "Hello from Spring Boot!";
	}
}
