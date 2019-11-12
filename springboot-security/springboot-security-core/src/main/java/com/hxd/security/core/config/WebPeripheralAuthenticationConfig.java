package com.hxd.security.core.config;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.hxd.security.core.authentication.PeripheralAuthenticationFilter;
import com.hxd.security.core.authentication.PeripheralAuthenticationProvider;
import com.hxd.security.core.cache.WebUserCache;
import com.hxd.security.core.cache.WebUserConCurrentMapCache;
import com.hxd.security.core.handler.FailureHandler;
import com.hxd.security.core.handler.SuccessHandler;

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
	
	@Bean
	private WebUserCache cache() {
		ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		User user = new User("15718884785","",AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		map.put("15718884785", user);
		return new WebUserConCurrentMapCache(map);
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
		peripheralAuthenticationProvider.setWebUserCache(cache());

		// 将自定义的provider 加入到Spring  provider 列表中
		http.authenticationProvider(peripheralAuthenticationProvider)
			//自定义的filter 加入到 过滤器链中 加在 UsernamePasswordAuthenticationFilter 后边
			.addFilterAfter(peripheralAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
