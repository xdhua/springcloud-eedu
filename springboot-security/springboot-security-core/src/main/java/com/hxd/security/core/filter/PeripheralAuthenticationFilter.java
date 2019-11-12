/**
 * 
 */
package com.hxd.security.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hxd.security.core.cache.WebUserCache;
import com.hxd.security.core.utils.CaptchaUtil;

/**
 * 添加  图片验证码 拦截
 * @author hxd
 *
 * date 2019年11月8日
 *  OncePerRequestFilter 只拦截request 请求
 */
public class PeripheralAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private WebUserCache webUserCache;
	/**
	 *  图片验证码， 可以再该方法上 添加 验证 token信息 方案
	 *  如果 当前设置 session 无效的话， 那SecurityContext 中就不存在 Authentication 信息
	 *  那么验证就失败
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//  图片验证码  是否有效
		if(org.apache.commons.lang3.StringUtils.equals(request.getRequestURI(), "/peripheral/login")) {
			String imagecode = request.getParameter("imagecode");
			if(CaptchaUtil.verify(imagecode)) {
				filterChain.doFilter(request, response);
			} else {
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().write("{\"code\":\"1\",\"msg\":\"验证码错误！\"}");
			}
		}else {
			filterChain.doFilter(request, response);
		}

	}

}
