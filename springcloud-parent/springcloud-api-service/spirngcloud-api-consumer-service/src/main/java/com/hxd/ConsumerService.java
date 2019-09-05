package com.hxd;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ConsumerService{
	
	/**
	 * 设置对外接口
	 * @param name 接受参数
	 * @return String
	 */
	@RequestMapping("/getName")
	public String getName(@RequestParam("name") String name);

}
