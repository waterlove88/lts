package com.wt.openapi.common.model;


import java.io.Serializable;

import javax.annotation.Resource;

//import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;


@Resource
public class RequestCustomHeaderInfo implements Serializable {
	
	public RequestCustomHeaderInfo() {}

	/** serialVersionUID */
	private static final long serialVersionUID = 6040239285143800645L;
	
	/** Sessoinid */
	private String sessionid;
	
	/** 사용자 POC 구분  [
		0 : Internal
		1 : Project1(실증연동 정신건강 App)
		2 : Project2(실증연동 운동량 App)
		3 : Project3(실증연동 정신건강 App)
		4 : Project4(실증연동 다이어트 App)
	]*/
	private String pocId;
	
	/** 사용자 ID */
	private String userId;
	
	/** Client IP */
	private String userIp;

	/** 사용자 MDN - 접속한 번호 */
	private String userMdn;
	
	/** 휴대폰 모델 */
	private String userHsmodel;
	
	private String userAgent;
	
	/** errorCode */
	private String errorCode;
	
	/** memNo */
	private String memNo;

	public String getMemNo() {
		return memNo;
	}

	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getPocId() {
		return pocId;
	}

	public void setPocId(String pocId) {
		this.pocId = pocId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getUserMdn() {
		return userMdn;
	}

	public void setUserMdn(String userMdn) {
		this.userMdn = userMdn;
	}

	public String getUserHsmodel() {
		return userHsmodel;
	}

	public void setUserHsmodel(String userHsmodel) {
		this.userHsmodel = userHsmodel;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
}
