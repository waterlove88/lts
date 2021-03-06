package com.wt.openapi.common.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.wt.openapi.common.model.RequestCustomHeaderInfo;
import com.wt.openapi.common.session.SessionCheckInfoByDB;
import com.wt.openapi.utils.CodeMessage;
import com.wt.openapi.utils.StringUtils;

@Controller
public class GeneralController {
	
	@Autowired
	SessionCheckInfoByDB sessionCheckInfo;
	
	protected Log logger = LogFactory.getLog(getClass());
	
	public RequestCustomHeaderInfo getSessionRequestCustomHeaderInfo(HttpServletRequest request) {
		
		RequestCustomHeaderInfo requestCustomHeaderInfo = new RequestCustomHeaderInfo();
//		SessionCheckInfoByDB sessionCheckInfo = new SessionCheckInfoByDB();
		
		try {
			logger.debug("#@ >> request : " + request);
			
			// 세션정보조회
			String sessionId = "";
			sessionId = StringUtils.nvlStr(request.getHeader("session-id"), "0");
			
			if (logger.isDebugEnabled()) {
				logger.debug("#@ >> sessionId = "+sessionId);
			}
			
			requestCustomHeaderInfo = sessionCheckInfo.getSessionCheckInfo(sessionId);
			
			// Null 값이 들어올 수 없는 값 세팅 null 일경우 강제 Exceiption 발생 시킴
			requestCustomHeaderInfo.getMemNo().toString();
			logger.info("requestCustomHeaderInfo.getMemNo().toString() : " + requestCustomHeaderInfo.getMemNo().toString());
			requestCustomHeaderInfo.getPocId().toString();
			logger.info("requestCustomHeaderInfo.getPocId().toString() : " + requestCustomHeaderInfo.getPocId().toString());
			
			if (logger.isDebugEnabled()) {
				logger.debug("#@ >> Session Login OK!!");
			}
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_OK);
			
		} catch (Exception ne) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_NO_SESSION), ne);
			//ne.printStackTrace();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		return requestCustomHeaderInfo;
	}
	
	/*
	 * 미로그인 사용자의 세션정보를 체크
	 */
	public RequestCustomHeaderInfo geNotLoginRequestHeaderInfo(HttpServletRequest request) {
		
		RequestCustomHeaderInfo requestCustomHeaderInfo = new RequestCustomHeaderInfo();
		
		try {
			logger.debug("#@ >> request : " + request);

			String pocId = "0";
			
			if (StringUtils.isNVL((String)request.getAttribute("server"))) {
				pocId = request.getHeader("poc-id").toString();
			}

			
			if(checkPocId(pocId)) {
				requestCustomHeaderInfo.setPocId(pocId);
				requestCustomHeaderInfo.setUserIp(StringUtils.nvlStr(request.getHeader("user-ip")));
				requestCustomHeaderInfo.setUserAgent(StringUtils.nvlStr(request.getHeader("user-agent")));
				requestCustomHeaderInfo.setUserMdn(StringUtils.nvlStr(request.getHeader("user-mdn")));
				requestCustomHeaderInfo.setUserHsmodel(StringUtils.nvlStr(request.getHeader("user-hsmodel")));
				requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_OK);
			} else {
				requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NOT_PARAMETER);
			}
			
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_NOT_PARAMETER), e);
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NOT_PARAMETER);
		}
		
		return requestCustomHeaderInfo;
	}
	
	/* user-channel 검증 */
	private boolean checkPocId(String pocId) {
		boolean checkResult = false;
		
		if(pocId.equals(CodeMessage.POC_ID_INERNAL)
			|| pocId.equals(CodeMessage.POC_ID_P1)
			|| pocId.equals(CodeMessage.POC_ID_P2)
			|| pocId.equals(CodeMessage.POC_ID_P3)
			|| pocId.equals(CodeMessage.POC_ID_P4)) {
			checkResult = true;
		}
		return checkResult;
	}
}
