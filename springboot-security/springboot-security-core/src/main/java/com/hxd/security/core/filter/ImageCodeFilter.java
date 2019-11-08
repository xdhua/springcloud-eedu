/**
 * 
 */
package com.hxd.security.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.hxd.security.core.utils.CaptchaUtil;

/**
 * 添加  图片验证码 拦截
 * @author hxd
 *
 * date 2019年11月8日
 *  OncePerRequestFilter 只拦截request 请求
 */
public class ImageCodeFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(org.apache.commons.lang3.StringUtils.equals(request.getRequestURI(), "/test/user/login")) {
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
