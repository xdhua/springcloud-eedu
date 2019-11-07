package com.hxd.security.demo.model;

import io.swagger.annotations.ApiModelProperty;

public class UserInfo {
	
	@ApiModelProperty("姓名")
	private String name;
	
	@ApiModelProperty("年龄")
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
