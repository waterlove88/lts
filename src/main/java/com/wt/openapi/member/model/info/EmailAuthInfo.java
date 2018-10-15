package com.wt.openapi.member.model.info;

import org.hibernate.validator.constraints.NotBlank;

public class EmailAuthInfo {

	@NotBlank
	private String memNo;
	private String emailAuthKey;
	
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getEmailAuthKey() {
		return emailAuthKey;
	}
	public void setEmailAuthKey(String emailAuthKey) {
		this.emailAuthKey = emailAuthKey;
	}
	
	@Override
	public String toString() {
		return "EmailAuthInfo [memNo=" + memNo + ", emailAuthKey=" + emailAuthKey + "]";
	}
}
