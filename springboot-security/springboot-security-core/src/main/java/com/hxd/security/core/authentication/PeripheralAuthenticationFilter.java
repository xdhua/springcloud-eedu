/**
 * 
 */
package com.hxd.security.core.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

/**
 * 根据 UsernamePasswordAuthenticationFilter 进行改造
 * @author hxd
 *
 * date 2019年11月12日
 * 
 */
public class PeripheralAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	// ~ Static fields/initializers
		// =====================================================================================

		public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "peripheralName";

		private String peripheralNameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
		private boolean postOnly = true;

		// ~ Constructors
		// ===================================================================================================
		/**
		 * 设置默认登陆 路径
		 */
		public PeripheralAuthenticationFilter() {
			super(new AntPathRequestMatcher("/peripheral/login", "POST"));
		}

		// ~ Methods
		// ========================================================================================================
		/**
		 *  从request 中获取参数信息  然后再去验证
		 */
		public Authentication attemptAuthentication(HttpServletRequest request,
				HttpServletResponse response) throws AuthenticationException {
			if (postOnly && !request.getMethod().equals("POST")) {
				throw new AuthenticationServiceException(
						"Authentication method not supported: " + request.getMethod());
			}

			String peripheralName = obtainPeripheralName(request);

			if (peripheralName == null) {
				peripheralName = "";
			}

			peripheralName = peripheralName.trim();

			PeripheralAuthenticationToken authRequest = new PeripheralAuthenticationToken(
					peripheralName);

			// Allow subclasses to set the "details" property
			setDetails(request, authRequest);

			return this.getAuthenticationManager().authenticate(authRequest);
		}



		/**
		 * 从request 中获取 当前登陆设备名
		 * @param request
		 * @return
		 */
		protected String obtainPeripheralName(HttpServletRequest request) {
			return request.getParameter(peripheralNameParameter);
		}

		/**
		 * 将当前信息封装到对象中
		 */
		protected void setDetails(HttpServletRequest request,
				PeripheralAuthenticationToken authRequest) {
			authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
		}

		/**
		 * 设置设备名称
		 */
		public void setUsernameParameter(String peripheralNameParameter) {
			Assert.hasText(peripheralNameParameter, "Username parameter must not be empty or null");
			this.peripheralNameParameter = peripheralNameParameter;
		}


		/**
		 *  设置是否只有 post 请求能通过
		 */
		public void setPostOnly(boolean postOnly) {
			this.postOnly = postOnly;
		}

		public final String getPeripheralNameParameter() {
			return peripheralNameParameter;
		}
}
