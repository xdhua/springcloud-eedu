package com.hxd;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProviderService {
	
	@RequestMapping("/provider/getName")
	public String getName(@RequestParam("name") String name);

}
