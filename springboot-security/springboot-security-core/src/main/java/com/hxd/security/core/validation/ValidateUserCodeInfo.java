/**
 * 
 */
package com.hxd.security.core.validation;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author hxd
 *
 * date 2019年11月13日
 * 
 */
public class ValidateUserCodeInfo extends User {
	
	private int seconds = 180;
	
	// 当前时间 加上设置的有效时间
	private LocalDateTime deadline;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2570591991104943890L;

	/**
	 * @param username
	 * @param password
	 * @param authorities
	 */
	public ValidateUserCodeInfo(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.deadline = LocalDateTime.now().plusSeconds(seconds);
	}

	/**
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	public ValidateUserCodeInfo(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.deadline = LocalDateTime.now().plusSeconds(seconds);
	}
	
	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.deadline = LocalDateTime.now().plusSeconds(seconds);
		this.seconds = seconds;
	}

	public LocalDateTime getDeadline() {
		return this.deadline;
	}

}
