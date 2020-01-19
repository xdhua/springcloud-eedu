/**
 * 
 */
package com.hxd.security.core.social.qq.connection;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.hxd.security.core.social.qq.QQUserInfoService;

/**
 * QQ 适配器 
 * 主要是 根据ApiBinding 中获取的用户信息 进行适配
 * @author hxd
 *
 * date 2019年11月21日
 * 
 */
public class QQAdapter implements ApiAdapter<QQUserInfoService> {

	/***
	 * 判断当前链接是否 有效
	 */
	@Override
	public boolean test(QQUserInfoService api) {
		return true;
	}

	/**
	 *  将获取的 用户user 信息 和当前给出的connectionValues 进行绑定
	 */
	@Override
	public void setConnectionValues(QQUserInfoService api, ConnectionValues values) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserProfile fetchUserProfile(QQUserInfoService api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(QQUserInfoService api, String message) {
		// TODO Auto-generated method stub
		
	}

}
