package com.hxd.security.core.config;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.hxd.security.core.authentication.PeripheralAuthenticationFilter;
import com.hxd.security.core.authentication.PeripheralAuthenticationProvider;
import com.hxd.security.core.cache.WebUserCache;
import com.hxd.security.core.cache.WebUserConCurrentMapCache;
import com.hxd.security.core.handler.FailureHandler;
import com.hxd.security.core.handler.SuccessHandler;
import com.hxd.security.core.handler.UserLogoutSuccessHandler;
import com.hxd.security.core.properties.SecurityProperties;
import com.hxd.security.core.validation.ValidateUserCodeInfo;

/**
 *  设置config 文件配置
 * @author hxd
 *
 * date 2019年11月12日
 *
 */
@Component("webPeripheralAuthenticationConfig")
public class WebPeripheralAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	/**
	 * 将自己定义的 验证成功后操作注入
	 */
	@Autowired
	private SuccessHandler successHandler;

	/**
	 *  将自定义验证失败后操作注入
	 */
	@Autowired
	private FailureHandler failureHandler;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	private WebUserCache webUserCache() {
		ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		ValidateUserCodeInfo info = new ValidateUserCodeInfo("15718884785", "1571888", new HashSet<>());
		map.put("15718884785_sms_login", info);
		return new WebUserConCurrentMapCache(map);
	}
	
	@Bean
	@ConditionalOnMissingBean(UserLogoutSuccessHandler.class)
	private LogoutSuccessHandler logoutSuccessHandler() {
		return new UserLogoutSuccessHandler(securityProperties);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		PeripheralAuthenticationFilter peripheralAuthenticationFilter = new PeripheralAuthenticationFilter();
		// 将当前 AuthenticationManager 设置上
		peripheralAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		peripheralAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
		peripheralAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);

		PeripheralAuthenticationProvider peripheralAuthenticationProvider = new PeripheralAuthenticationProvider();
		peripheralAuthenticationProvider.setUserDetailsService(userDetailsService);
		peripheralAuthenticationProvider.setWebUserCache(webUserCache());

		// 将自定义的provider 加入到Spring  provider 列表中
		http.authenticationProvider(peripheralAuthenticationProvider)
			//自定义的filter 加入到 过滤器链中 加在 UsernamePasswordAuthenticationFilter 后边
			.addFilterAfter(peripheralAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
