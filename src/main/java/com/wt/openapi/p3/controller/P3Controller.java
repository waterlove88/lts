package com.wt.openapi.p3.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wt.openapi.common.controller.GeneralController;
import com.wt.openapi.common.model.RequestCustomHeaderInfo;
import com.wt.openapi.common.model.ResultCode;
import com.wt.openapi.common.model.ResultMaster;
import com.wt.openapi.p3.service.P3Service;
import com.wt.openapi.utils.CodeMessage;
import com.wt.openapi.utils.HttpRequest;

@RequestMapping(value = "/openapi/p3")
@Controller
public class P3Controller extends GeneralController {
	
	@Autowired
	P3Service p3Service;
	
	@RequestMapping(value = "/sleep", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResultMaster createSleep(HttpServletRequest request) throws Exception
	{
		ResultMaster rm = null;
		
		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();	
		
		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try {
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo(request);
		} catch(Exception e) {
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if(requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK)) {
			rm = p3Service.createSleep(requestCustomHeaderInfo, param);
		} else {
			rm = new ResultMaster();
			ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
			rm.setResult(result);
		}
		
		return rm;
	}
	
	@RequestMapping(value = "/sleep", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResultMaster getSleepList(HttpServletRequest request) throws Exception
	{
		ResultMaster rm = null;
		
		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();
		
		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try {
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo(request);
		} catch(Exception e) {
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if(requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK)) {
			rm = p3Service.getSleepList(requestCustomHeaderInfo, param);
		} else {
			rm = new ResultMaster();
			ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
			rm.setResult(result);
		}
				
		return rm;
	}
	
	@RequestMapping(value = "/sleep", method = RequestMethod.PUT, produces="application/json")
	@ResponseBody
	public ResultMaster updateSleep(HttpServletRequest request) throws Exception
	{
		ResultMaster rm = null;
		
		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();
		
		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try {
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo(request);
		} catch(Exception e) {
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if(requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK)) {
			rm = p3Service.updateSleep(requestCustomHeaderInfo, param);
		} else {
			rm = new ResultMaster();
			ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
			rm.setResult(result);
		}
				
		return rm;
	}
	
	@RequestMapping(value = "/sleep", method = RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	public ResultMaster deleteSleep(HttpServletRequest request) throws Exception
	{
		ResultMaster rm = null;
		
		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();
		
		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try {
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo(request);
		} catch(Exception e) {
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if(requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK)) {
			rm = p3Service.deleteSleep(requestCustomHeaderInfo, param);
		} else {
			rm = new ResultMaster();
			ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
			rm.setResult(result);
		}
				
		return rm;
	}

}
