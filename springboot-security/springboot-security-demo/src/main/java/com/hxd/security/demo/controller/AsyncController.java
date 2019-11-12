package com.hxd.security.demo.controller;

import java.util.concurrent.Callable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hxd.security.demo.model.UserInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/async")
@Api(value = "/async Operations about async")
public class AsyncController {
	
	
	@RequestMapping(value = "/getName",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation("获取用户名------") // swagger 页面中 对当前方法进行的描述
	public UserInfo getName(@ApiParam("用户名") @RequestParam("name") String name) {
		UserInfo user = new UserInfo();
		user.setName(name);
		return user;
	}
	
	
	@RequestMapping(value = "/getAsyncName",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation("测试------") // swagger 页面中 对当前方法进行的描述
	public Callable<String> getAsyncName(@ApiParam("用户名") @RequestParam("name") String name) {
		Thread t = Thread.currentThread();
		System.out.println("请求开始"+ t.getName() + "时间为： " + System.currentTimeMillis());
//		在主线程中开启新的线程进行业务逻辑调用  减少服务器单线程占用时间 增加
//		服务器吞吐量
		Callable<String> result = ()->{
			Thread t1 = Thread.currentThread();
			System.out.println("异步开始"+ t1.getName() + "时间为： " + System.currentTimeMillis());
			Thread.sleep(2000);
			System.out.println("异步结束"+ t1.getName() + "时间为： " + System.currentTimeMillis());
			return "hxd";
		};
		System.out.println("请求结束"+ t.getName() + "时间为： " + System.currentTimeMillis());
		return result;
	}
	
	
//	@RequestMapping(value = "/getAsyncName",method = RequestMethod.GET)
//	@ResponseBody
//	public Callable<String> getAsyncName() {
//		Thread t = Thread.currentThread();
//		System.out.println("请求开始"+ t.getName() + "时间为： " + System.currentTimeMillis());
////		在主线程中开启新的线程进行业务逻辑调用  减少服务器单线程占用时间 增加
////		服务器吞吐量
//		Callable<String> result = ()->{
//			Thread t1 = Thread.currentThread();
//			System.out.println("异步开始"+ t1.getName() + "时间为： " + System.currentTimeMillis());
//			Thread.sleep(2000);
//			System.out.println("异步结束"+ t1.getName() + "时间为： " + System.currentTimeMillis());
//			return "hxd";
//		};
//		System.out.println("请求结束"+ t.getName() + "时间为： " + System.currentTimeMillis());
//		return result;
//	}

}
