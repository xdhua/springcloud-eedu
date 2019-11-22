/**
 * 
 */
package com.hxd.security.core.social.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;

/**
 * @author hxd
 *
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
	
	
	/**
	 *  设置 获取 user 仓库获取存储  默认写入内存中
	 */
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
//		// 第三个参数是对插入到数据库中的数据做加密，这里为了看的清楚，没有做任何处理，即noOpText
//		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
//				connectionFactoryLocator, Encryptors.noOpText());
//        // 为表名增加前缀
//		repository.setTablePrefix("imooc_");
//		if(connectionSignUp != null) {
//			repository.setConnectionSignUp(connectionSignUp);
//		}
//		return repository;
		return null;
	}

}
