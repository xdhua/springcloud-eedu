package com.hxd.security.broswer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.hxd.security.broswer.handler.FailHandler;
import com.hxd.security.broswer.handler.SuccessHandler;
import com.hxd.security.broswer.remberme.WebUserRemembermeTokenRepository;
import com.hxd.security.core.filter.ImageCodeFilter;
import com.hxd.security.core.properties.SecurityProperties;

/**
 * spring security 的安全配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 *  将自定义的 properties 文件 注入进来
	 */
	@Autowired
	private SecurityProperties securityProperties; 
	
	/**
	 * 将自己定义的 验证成功后操作注入
	 */
	@Autowired
	private SuccessHandler successHandler;
	
	/**
	 *  将自定义验证失败后操作注入
	 */
	@Autowired
	private FailHandler FailHandler;
	
	/**
	 * BCrypt密码编码器
	 * 在用户注册时候调用encode 将密码加密 后在写入数据库
	 * 
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * 设置remberMe 的设置
	 * @return
	 */
	@Bean
	public  PersistentTokenRepository persistentTokenRepository() {
		WebUserRemembermeTokenRepository tokenRepositoryImpl = new WebUserRemembermeTokenRepository();
		return tokenRepositoryImpl;
	}
	
	/**
	 * 将 自己实现的 数据库验证的 获取 userDetails 的接口注入进来
	 */
	@Autowired
	private UserDetailsService userDetailsService;
	

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.addFilterBefore(new ImageCodeFilter(), UsernamePasswordAuthenticationFilter.class)  // 将自定义Filter 加在UsernamePasswordAuthenticationFilter 之前 
		.formLogin()
			.loginPage("/redirect/handler")  //所有没有验证的请求 都跳转到当前url中
			.loginProcessingUrl("/test/user/login") // 替换掉默认/login post操作请求到服务器
			.successHandler(successHandler) // 设置验证成功后 操作
			.failureHandler(FailHandler) // 验证失败后操作
			.and()
		.rememberMe() //设置记住我
//		.rememberMeParameter("rememberMe") // 设置请求参数 为rememberMe 默认为remember-me
			.tokenRepository(persistentTokenRepository()) // 设置 记住我的持久化仓库
			.tokenValiditySeconds(50) // 设置 记住我时间
			.userDetailsService(userDetailsService)  // 设置获取userDetails service
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 禁用session
//		.and()
//		.cors().and() // 跨域
		.and()
		.csrf().disable()  //解决 自定义页面不跳转问题
		.authorizeRequests()
		.antMatchers("/redirect/handler",
				securityProperties.getLoginUrl(),
				"/images/captcha").permitAll() // 添加不需要验证的路径
		// 除上面外的所有请求全部需要鉴权认证
		.anyRequest().authenticated();
	}
}
