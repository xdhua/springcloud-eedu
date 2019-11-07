package com.hxd.security.broswer.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hxd.security.core.properties.SecurityProperties;

/**
 *  自定义登录成功操作器
 *  继承 默认实现类 SavedRequestAwareAuthenticationSuccessHandler
 * @author hxd
 *
 * 2019年11月7日
 */
@Component("successHandler")
public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  {

	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 *  将自定义的 properties 文件 注入进来
	 */
	@Autowired
	private SecurityProperties securityProperties; 
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// 判断是否走自己的逻辑
		if(securityProperties.isReturnJson()) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		}else {
			//走默认逻辑
			super.onAuthenticationSuccess(request, response, authentication);
		}
		
	}
	

}
