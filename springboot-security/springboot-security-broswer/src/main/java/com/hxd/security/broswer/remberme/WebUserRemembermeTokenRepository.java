package com.hxd.security.broswer.remberme;

import java.util.Date;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 *  自定义 实现renmberMe 中 数据保存 更新 生成删除操作
 * @author hxd
 *
 * date 2019年11月11日
 *
 */
public class WebUserRemembermeTokenRepository implements PersistentTokenRepository {
	
	/**
	 * 1. 自定义表
	 *  根据jdbc 实现来看表是这样的 也可以自定义设计主要生成对象PersistentRememberMeToken
	 * username   series(主键)      token     last_used
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	/**
	 *  表的插入操作
	 */
	@Override
	public void createNewToken(PersistentRememberMeToken token) {

	}
	
	/**
	 * 更新操作
	 */
	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {

	}
	
	/**
	 *  查询操作
	 */
	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		return null;
	}
	/**
	 *  删除操作
	 */
	@Override
	public void removeUserTokens(String username) {

	}

}
