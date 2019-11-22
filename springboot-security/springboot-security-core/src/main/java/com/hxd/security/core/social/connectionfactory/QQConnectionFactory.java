/**
 * 
 */
package com.hxd.security.core.social.connectionfactory;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.hxd.security.core.social.apiadapter.QQAdapter;
import com.hxd.security.core.social.qq.QQServiceProvider;
import com.hxd.security.core.social.qq.QQUserInfoService;

/**
 * @author hxd
 * 
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQUserInfoService> {

	/**
	 * 
	 * @param providerId 服务提供商的唯一标识
	 * @param serviceProvider 带有 授权路径  获取token 路径的 provider
	 * @param apiAdapter 将获取到的用户信息  设置在 connectionValues 上
	 * @param appId 项目注册到qq 分配给的Id
	 * @param appSecret 项目注册到 第三方应用获取的Id
	 * 
	 */
	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}
