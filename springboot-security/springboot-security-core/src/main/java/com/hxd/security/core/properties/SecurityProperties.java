package com.hxd.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *  配置信息
 * @author hxd
 *
 * 2019年11月7日
 */
@Component
@PropertySource("classpath:security.properties")
@ConfigurationProperties(prefix = "test")
public class SecurityProperties {
	
	private String loginUrl = "/login.html";
	
	private boolean isReturnJson;
	
	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	
	@Override
	public String toString() {
		return "SecurityProperties [loginUrl=" + loginUrl + "]";
	}

	public boolean isReturnJson() {
		return isReturnJson;
	}

	public void setReturnJson(boolean isReturnJson) {
		this.isReturnJson = isReturnJson;
	}

}
