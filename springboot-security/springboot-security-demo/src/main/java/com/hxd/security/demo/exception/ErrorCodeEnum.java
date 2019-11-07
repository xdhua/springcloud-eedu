package com.hxd.security.demo.exception;

/**
 * http 请求错误信息
 * 
 */
public enum ErrorCodeEnum {
	/** General OK **/
	RES_OK(0x00, "操作成功"),

	/** 操作失败 **/
	RES_ERROR_GENERAL(133, "操作失败"),
	
	/** 用户名或密码错误 **/
	RES_ERROR_USER(132, "用户名或密码错误"),
	
	/** 用户被锁定  **/
	RES_ERROR_USER_LOCKED(130, "该用户已被锁定");	

	private String msg;
	private Integer code;

	ErrorCodeEnum(Integer code, String msg) {
		this.msg = msg;
		this.code = code;
	}

	public static String getName(int code) {
		for (ErrorCodeEnum e : ErrorCodeEnum.values()) {
			if (e.getCode() == code) {
				return e.getMsg();
			}
		}
		return null;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
