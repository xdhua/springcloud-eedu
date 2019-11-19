/**
 * 
 */
package com.hxd.security.core.filter;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hxd.security.core.cache.WebUserCache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hxd.security.core.validation.ValidateCodeInfo;

/**
 * 添加  图片验证码 拦截
 * @author hxd
 *
 * date 2019年11月8日
 *  OncePerRequestFilter 只拦截request 请求
 */
public class ImageCodeFilter extends OncePerRequestFilter {
	
	private WebUserCache webUserCache;
	
	public void setWebUserCache(WebUserCache webUserCache) {
		this.webUserCache = webUserCache;
	}
	public ImageCodeFilter(WebUserCache webUserCache) {
		this.webUserCache = webUserCache;
	}

	/**
	 *  图片验证码， 可以再该方法上 添加 验证 token信息 方案
	 *  如果 当前设置 session 无效的话， 那SecurityContext 中就不存在 Authentication 信息
	 *  那么验证就失败
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//  图片验证码  是否有效
		if(org.apache.commons.lang3.StringUtils.equals(request.getRequestURI(), "/test/user/login")) {
			String imagecode = request.getParameter("imagecode");
			String username = request.getParameter("username");
			if(verify(imagecode, username)) {
				filterChain.doFilter(request, response);
			} else {
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().write("{\"code\":\"1\",\"msg\":\"验证码错误！\"}");
			}
		}else {
			filterChain.doFilter(request, response);
		}
	}
	
	private  boolean verify(String code,String key) {
    	if(webUserCache.containsKey(key)) {
    		ValidateCodeInfo info = (ValidateCodeInfo)webUserCache.remove(key);
    		if(LocalDateTime.now().isAfter(info.getDeadline())) {
    			return false;
    		}
            return StringUtils.equalsIgnoreCase(code, info.getCode());
    	}else {
    		return false;
    	}
    }

}
