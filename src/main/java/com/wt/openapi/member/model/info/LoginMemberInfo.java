package com.wt.openapi.member.model.info;

import javax.validation.constraints.Pattern;

public class LoginMemberInfo {

	@Pattern(regexp="^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$")
    private String userId;
    
    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[!@#$%^*=-])(?=.*[0-9]).{8,}$")
    private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
