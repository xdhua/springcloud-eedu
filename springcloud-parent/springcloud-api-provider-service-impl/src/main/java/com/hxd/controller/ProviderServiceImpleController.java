package com.hxd.controller;

import org.springframework.web.bind.annotation.RestController;

import com.hxd.ProviderService;
import com.hxd.model.User;

@RestController
public class ProviderServiceImpleController implements ProviderService {

	@Override
	public String getName(User user) {
		String result = "provider controller" + user;
		return result;
	}

}
