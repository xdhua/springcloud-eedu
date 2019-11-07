/**
 * 
 */
package com.hxd.security.demo.controller;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author hxd
 *
 * 2019年11月5日
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@PostMapping("/login")
	@ApiOperation("登录接口")
	public String login(@ApiParam("用户名") @RequestParam("username") String username, 
			@ApiParam("密码") @RequestParam("password") String password,AuthenticateAction authenticateAction) {
		return null;
	}
	
	@GetMapping("/me")
	@ApiOperation("登录接口")
	public Authentication me(Authentication authentication) {
		return authentication;
	}

}
