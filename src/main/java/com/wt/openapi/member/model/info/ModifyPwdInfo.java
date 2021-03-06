package com.wt.openapi.member.model.info;

import javax.validation.constraints.Pattern;

public class ModifyPwdInfo {
	@Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[!@#$%^*=-])(?=.*[0-9]).{8,}$")
    private String memPw;
	
	@Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[!@#$%^*=-])(?=.*[0-9]).{8,}$")
    private String changePw;

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	public String getChangePw() {
		return changePw;
	}

	public void setChangePw(String changePw) {
		this.changePw = changePw;
	}
}
