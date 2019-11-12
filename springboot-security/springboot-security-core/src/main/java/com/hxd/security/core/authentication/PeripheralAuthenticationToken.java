/**
 * 
 */
package com.hxd.security.core.authentication;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

/**
 * @author hxd
 *
 * date 2019年11月12日
 * 
 */
public class PeripheralAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	// 当前登陆设备信息
	private final Object peripheralName;
	
	// 当前登录验证码
	private final String msg;

	// ~ Constructors
	// ===================================================================================================

	/**
	 *  设置当前登陆 设备信息
	 * @param principal
	 */
	public PeripheralAuthenticationToken(Object principal, String msg) {
		super(null);
		this.peripheralName = principal;
		this.msg = msg;
		setAuthenticated(false);
	}

	/**
	 * 
	 * @param principal 当前外设名称 手机  邮件 等等
	 * @param authorities 当前登陆人获得权限
	 * 设置当前Authentication 已经认证通过
	 */
	public PeripheralAuthenticationToken(Object principal, String msg,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.msg = msg;
		this.peripheralName = principal;
		super.setAuthenticated(true); // must use super, as we override
	}

	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}

		super.setAuthenticated(false);
	}

	@Override
	public Object getCredentials() {
		return this.msg;
	}

	@Override
	public Object getPrincipal() {
		return this.peripheralName;
	}

}
