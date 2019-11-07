package com.hxd.security.broswer.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hxd.security.core.properties.SecurityProperties;

/**
 *  自定义登录失败操作器
 *  继承默认处理器 SimpleUrlAuthenticationFailureHandler 重写 onAuthenticationFailure 方法
 * @author hxd
 *
 * 2019年11月7日
 */
@Component("failHandler")
public class FailHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private ObjectMapper objectMapper;
	/**
	 *  将自定义的 properties 文件 注入进来
	 */
	@Autowired
	private SecurityProperties securityProperties; 

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		if(securityProperties.isReturnJson()) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(exception));
		}else {
			super.onAuthenticationFailure(request, response, exception);
		}
	}
	
	

}
