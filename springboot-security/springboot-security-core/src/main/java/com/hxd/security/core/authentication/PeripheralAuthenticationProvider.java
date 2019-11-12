package com.hxd.security.core.authentication;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.hxd.security.core.cache.WebUserCache;
import com.hxd.security.core.cache.WebUserNullCache;

/**
 *  仿照 rememberMe provider进行实现
 * @author hxd
 *
 * date 2019年11月12日
 *
 */
public class PeripheralAuthenticationProvider implements AuthenticationProvider {

	protected final Log logger = LogFactory.getLog(getClass());

	// ~ Instance fields
	// ================================================================================================

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	private UserDetailsService userDetailsService;

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	private WebUserCache webUserCache = new WebUserNullCache();

	public void setWebUserCache(WebUserCache webUserCache) {
		this.webUserCache = webUserCache;
	}

	/**
	 * 实现思路
	 * 从 authentication 中获取 设备名称 然后在通过 设备名称去
	 *  UserDetailService的实现中获取 当前设备名称是否有资格登陆
	 *   如果没问题 那么将返回封装的 UserDetails 然后返回一个验证通过的 Authentication
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		PeripheralAuthenticationToken token = (PeripheralAuthenticationToken)authentication;
		String peripheralName = (String)token.getPrincipal();
		UserDetails user = null;
		boolean hasCaChe = this.webUserCache.containsKey(peripheralName);
		
		if(hasCaChe) {
			user = (UserDetails)this.webUserCache.get(peripheralName);
		}else {
			user = userDetailsService.loadUserByUsername(peripheralName);
		}

		if(user == null) {
			throw new InternalAuthenticationServiceException("用户信息验证失败");
		}
		//TODO 验证当前验证码是否过期
		
		PeripheralAuthenticationToken peripheralAuthenticationToken = new PeripheralAuthenticationToken(user.getUsername(),user.getPassword(),user.getAuthorities());
		this.checkNumber(user, peripheralAuthenticationToken);
		return peripheralAuthenticationToken;
	}

	/**
	 *  是否支持的类型
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return PeripheralAuthenticationToken.class.isAssignableFrom(authentication);
	}

	/**
	 *  验证当前验证码是否通过
	 * @param userDetails
	 * @param authentication
	 * @throws AuthenticationException
	 */
	private void checkNumber(UserDetails userDetails,
			PeripheralAuthenticationToken authentication)
					throws AuthenticationException {
		if (authentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");
			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}

		String password = authentication.getCredentials().toString();

		if (StringUtils.equals(userDetails.getPassword(), password)) {
			logger.debug("Authentication failed: password does not match stored value");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}
	}

}

