package com.hxd.security.broswer.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hxd.security.core.properties.SecurityProperties;
import com.hxd.security.core.utils.CaptchaUtil;
import com.wf.captcha.base.Captcha;

/**
 *   将所有请求拦截
 * @author hxd
 *
 * 2019年11月7日
 */
@RestController
public class RedirectController {
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@RequestMapping("/redirect/handler")
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
	public String redirectHandler(HttpServletRequest request, HttpServletResponse response) {
		// 缓存的请求 比如  直接跳过 验证画面 访问其中的 url springSecurity 会将这个url进行拦截存储在 session中 进入配置的/redirect/handler方法
		// 即 当前方法
		SavedRequest sq = requestCache.getRequest(request, response);
		if(null != sq) {
			String requestUrl = sq.getRedirectUrl();
			//判断当前缓存的对象路径是个html静态页面的话，那么跳转到该页面
			if(StringUtils.endsWith(requestUrl, ".html")) {
				try {
					// 跳转到哪个页面
					// 可以再设置一个properties 文件记录跳转到哪个画面 requestUrl  然后读取 配置文件中的 url 进行跳转
					new DefaultRedirectStrategy().sendRedirect(request, response, securityProperties.getLoginUrl());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "错误信息";
	}
	
    @GetMapping("/images/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.outPng(180, 34, 6, Captcha.TYPE_NUM_AND_UPPER, request, response);
    }

}
