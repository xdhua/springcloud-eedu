package com.hxd.security.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器 区别 filter
 * @author hxd
 *
 * 2019年11月4日
 */
public class WebInterceptor implements HandlerInterceptor {
	
	/***
	 *  执行方法之前执行
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
//		HandlerMethod method =  (HandlerMethod)handler;
//		// 获取当前请求路径 请求的哪个类
//		method.getBean().getClass().getName();
//		// 获取方法名
//		method.getMethod().getName();
					
		return true;
	}
	
	/**
	 *  请求处理完成后 走的方法  异常不走
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 方法执行完成之后走  不管是否异常都会走 当前方法
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
	}

}
