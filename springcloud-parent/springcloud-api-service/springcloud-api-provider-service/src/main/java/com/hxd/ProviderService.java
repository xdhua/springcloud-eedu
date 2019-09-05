package com.hxd;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hxd.model.User;

public interface ProviderService {
	
	@RequestMapping("/provider/getName")
	public String getName(@RequestBody User user);

}
