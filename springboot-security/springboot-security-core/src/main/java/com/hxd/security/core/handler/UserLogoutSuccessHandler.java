/**
 * 
 */
package com.hxd.security.core.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.hxd.security.core.properties.SecurityProperties;

/**
 * 正常logout 操作
 * @author hxd
 *
 * date 2019年11月15日
 * 
 */
public class UserLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler 
										implements LogoutSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 *  将自定义的 properties 文件 注入进来
	 */
	private SecurityProperties securityProperties; 
	
	public UserLogoutSuccessHandler(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		if(securityProperties.isReturnJson()) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		}else {
			// logOut 默认操作 跳转页面
			super.handle(request, response, authentication);
		}
		
	}

}
