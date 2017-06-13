package com.wt.openapi.member.model.vo;

public class MemberPwdInfo {
	private String memNo;
	private String memPassword;
	private String memSalt;
	private String createDate;
	private String modifyDate;
	
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getMemPassword() {
		return memPassword;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	public String getMemSalt() {
		return memSalt;
	}
	public void setMemSalt(String memSalt) {
		this.memSalt = memSalt;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
}
