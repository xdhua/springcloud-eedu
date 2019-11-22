/**
 * 
 */
package com.hxd.security.core.social.apiadapter;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.hxd.security.core.social.qq.QQUserInfo;
import com.hxd.security.core.social.qq.QQUserInfoService;

/**
 * @author hxd
 * 转换器 将获取到的  第三方用户信息 设置到 标准信息上
 *
 */
public class QQAdapter implements ApiAdapter<QQUserInfoService> {

	/**
	 * p判定连接是否可用
	 */
	@Override
	public boolean test(QQUserInfoService api) {
		return true;
	}
	
	/**
	 *  将获取的user信息设置到适配器上
	 */
	@Override
	public void setConnectionValues(QQUserInfoService api, ConnectionValues values) {
		
		QQUserInfo info = api.getUserInfo();
		// 设置用户昵称
		values.setDisplayName(info.getNickname());
		
		// 设置主页url 类似微博  点自己头像看自己的历史活动
		values.setProfileUrl(null);
		
		// 设置头像
		values.setImageUrl(info.getFigureurl_qq_1());
		
		// 设置 在服务提供商的  唯一号
		values.setProviderUserId(info.getOpenId());
		
	}

	/**
	 *  通过获取到的服务商提供的 个人信息封装成标准识别信息
	 */
	@Override
	public UserProfile fetchUserProfile(QQUserInfoService api) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 类似 调用微博 登录时候 发布一条信息
	 */
	@Override
	public void updateStatus(QQUserInfoService api, String message) {
		
	}

}
