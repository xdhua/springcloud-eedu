package com.hxd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hxd.ConsumerService;
import com.hxd.feign.ConsumerFeignService;
import com.hxd.model.User;

@RestController
public class ConsumerServiceImplController implements ConsumerService {
	
	@Autowired
	private ConsumerFeignService consumerFeignService;
	
	@RequestMapping("/getName")
	public String getName(@RequestParam("name") String name) {
		User user = new User();
		user.setAge((short)18);
		user.setName(name);
		user.setEmail("xdhua@126.com");
		user.setSex((byte)1);
		return consumerFeignService.getName(user);
	}

}
