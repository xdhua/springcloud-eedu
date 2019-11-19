/**
 * 
 */
package com.hxd.security.broswer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hxd.security.core.cache.WebUserCache;
import com.hxd.security.core.utils.CaptchaUtil;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.base.Captcha;

/**
 * 验证码校验中心 
 * 1.图形码
 * 2.短信
 * @author hxd
 *
 * date 2019年11月12日
 * 
 */
@RestController
public class ValidateCodeController {
	
	@Autowired
	private WebUserCache webUserCache;
	
	/**
	 *  图形码验证器
	 * @param request HttpServletRequest 请求
	 * @param response HttpServletResponse 响应
	 * @throws Exception 异常信息
	 */
    @GetMapping("/images/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Captcha captcha =  CaptchaUtil.outPng(180, 34, 6, Captcha.TYPE_NUM_AND_UPPER, request, response);
    	captcha.out(response.getOutputStream());
    	setHeader(response,captcha);
    	webUserCache.put("user", captcha.text());
    }
    
    public  void setHeader(HttpServletResponse response, Captcha captcha) {
        if (GifCaptcha.class.isAssignableFrom(captcha.getClass())) {
            response.setContentType("image/gif");
        } else {
            response.setContentType("image/png");
        }
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
    }

}
