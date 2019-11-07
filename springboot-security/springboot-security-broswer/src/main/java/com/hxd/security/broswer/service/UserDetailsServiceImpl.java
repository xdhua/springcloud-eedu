package com.hxd.security.broswer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/***
 * 
 * @author hxd
 *
 * 2019年11月4日
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 *  重写loadUserByUsername  根据用户名 去业务中查找当前用户是否存在在比较 用户密码是否一致还有权限
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		参数说明 usename  用户名  ， 用户密码 当前用户查询出来的密码， 用户权限集合
		User user = new User(username,passwordEncoder.encode("123456"),AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		return user;
	}

}
