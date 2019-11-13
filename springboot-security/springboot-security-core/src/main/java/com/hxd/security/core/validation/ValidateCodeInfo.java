/**
 * 
 */
package com.hxd.security.core.validation;

import java.time.LocalDateTime;

/**
 * 验证码存储信息
 * 包括验证码  验证码有效时间
 * @author hxd
 *
 * date 2019年11月13日
 * 
 */
public class ValidateCodeInfo {
	
	private String code;
	
	private int seconds = 180;
	
	// 当前时间 加上设置的有效时间
	private LocalDateTime deadline;
	
	public ValidateCodeInfo(int seconds) {
		this.seconds = seconds;
	}
	
	public ValidateCodeInfo(String code, int seconds) {
		this(seconds);
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getDeadline() {
		return this.deadline;
	}

	public int getSeconds() {
		this.deadline = LocalDateTime.now().plusSeconds(seconds);
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	@Override
	public String toString() {
		return "ValidateCodeInfo [code=" + code + ", seconds=" + seconds + ", deadline=" + deadline + "]";
	}

}
