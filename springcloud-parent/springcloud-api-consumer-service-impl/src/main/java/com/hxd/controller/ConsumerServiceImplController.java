package com.hxd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hxd.ConsumerService;

@RestController
public class ConsumerServiceImplController {
	
	@Autowired
	private ConsumerService consumerService;
	
	@RequestMapping("/getName")
	public String getName(@RequestParam("name") String name) {
		
		return consumerService.getName(name);
	}

}
