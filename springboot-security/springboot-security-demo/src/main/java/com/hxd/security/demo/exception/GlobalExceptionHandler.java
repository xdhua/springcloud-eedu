package com.hxd.security.demo.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/***
	 *   对于没有处理 统一抛出的异常捕获处理
	 * @param req request  请求对象
	 * @param ex 具体异常
	 * @return 返回信息
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 如果异常统一返回 500
	public Map<String, Object> defaultErrorHandler(HttpServletRequest req, Exception ex) {
		Map<String, Object> map = new HashMap<String, Object>();
		ErrorCodeEnum errEnum;
//		if (ex instanceof BadCredentialsException) {
//			errEnum = ErrorCodeEnum.RES_ERROR_USER;
//			map.put("code", errEnum.getCode());
//			map.put("msg", errEnum.getMsg());
//		} else if(ex instanceof LockedException){
//			errEnum = ErrorCodeEnum.RES_ERROR_USER_LOCKED;
//			map.put("code", errEnum.getCode());
//			map.put("msg", errEnum.getMsg());
//		}else {
			errEnum = ErrorCodeEnum.RES_ERROR_GENERAL;
			map.put("code", errEnum.getCode());
			map.put("msg", errEnum.getMsg());
//		}
		map.put("url", req.getRequestURL());
		map.put("stackTrace", ex.getStackTrace());
		map.put("exception", ex);
		if (logger.isInfoEnabled()){
			logger.error(ex.getMessage(), map);
			logger.error("error stack:", ex);
		}
		return map;
	}
}
