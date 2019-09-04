package com.hxd.controller;

import org.springframework.web.bind.annotation.RestController;

import com.hxd.ProviderService;

@RestController
public class ProviderServiceImpleController implements ProviderService {

	@Override
	public String getName(String name) {
		String result = "provider controller" + name;
		return result;
	}

}
