package com.hxd.security.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hxd.security.demo.interceptor.WebInterceptor;

/**
 * @author hxd
 * 2019年11月1日
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	
	/**
	 *  加载拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new WebInterceptor());
	}
	
	/**
	 * 添加外部 或者自定义不带@comment 注解的 Filer 到Filer 链表中
	 * @return
	 */
//	@Bean
//	public FilterRegistrationBean<Filter> registrationFilterBean(){
//		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
////		将Filter  实例化 增加到 filter 注册
////		MyFilter myFilter = new MyFilter();
////		filterRegistrationBean.setFilter(myFilter);
//		
//		// 标明自定义的 Filter 在哪些请求上起效果
//		List<String> urls = Arrays.asList("/user/add","/user/delete");
//		filterRegistrationBean.setUrlPatterns(urls);
//		return filterRegistrationBean;
//	}

}
