package com.wt.openapi.p1.controller;

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
import com.wt.openapi.p1.service.P1Service;
import com.wt.openapi.utils.CodeMessage;
import com.wt.openapi.utils.HttpRequest;

@RequestMapping(value = "/openapi/p1")
@Controller
public class P1Controller extends GeneralController {
	
	@Autowired
	P1Service p1Service;
	
	@RequestMapping(value = "/emotion", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResultMaster createEmotion(HttpServletRequest request) throws Exception {
		
		ResultMaster rm = null; 

		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();

		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try{
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo( request );
		}catch(Exception e){
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if( requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK) ){
			rm = p1Service.createEmotion(requestCustomHeaderInfo, param);
        }else{
        	rm = new ResultMaster();
        	ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
        	rm.setResult(result);
        }
		 
		return rm;
	}
	
	@RequestMapping(value = "/emotion", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResultMaster getEmotionList(HttpServletRequest request) throws Exception {
		
		ResultMaster rm = null; 

		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();

		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try{
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo( request );
		}catch(Exception e){
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if( requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK) ){
			rm = p1Service.getEmotionList(requestCustomHeaderInfo, param);
        }else{
        	rm = new ResultMaster();
        	ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
        	rm.setResult(result);
        }
		 
		return rm;
	}
	
	@RequestMapping(value = "/emotion", method = RequestMethod.PUT, produces="application/json")
	@ResponseBody
	public ResultMaster updateEomtion(HttpServletRequest request) throws Exception {
		
		ResultMaster rm = null; 

		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();

		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try{
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo( request );
		}catch(Exception e){
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if( requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK) ){
			rm = p1Service.updateEomtion(requestCustomHeaderInfo, param);
        }else{
        	rm = new ResultMaster();
        	ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
        	rm.setResult(result);
        }
		 
		return rm;
	}
	
	@RequestMapping(value = "/emotion", method = RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	public ResultMaster deleteEomtion(HttpServletRequest request) throws Exception {
		
		ResultMaster rm = null; 

		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();

		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try{
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo( request );
		}catch(Exception e){
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if( requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK) ){
			rm = p1Service.deleteEomtion(requestCustomHeaderInfo, param);
        }else{
        	rm = new ResultMaster();
        	ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
        	rm.setResult(result);
        }
		 
		return rm;
	}
	
	@RequestMapping(value = "/furytree", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResultMaster createFurytree(HttpServletRequest request) throws Exception {
		
		ResultMaster rm = null; 

		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();

		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try{
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo( request );
		}catch(Exception e){
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if( requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK) ){
			rm = p1Service.createFurytree(requestCustomHeaderInfo, param);
        }else{
        	rm = new ResultMaster();
        	ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
        	rm.setResult(result);
        }
		 
		return rm;
	}
	
	@RequestMapping(value = "/furytree", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResultMaster getFurytreeList(HttpServletRequest request) throws Exception {
		
		ResultMaster rm = null; 

		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();

		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try{
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo( request );
		}catch(Exception e){
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if( requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK) ){
			rm = p1Service.getFurytreeList(requestCustomHeaderInfo, param);
        }else{
        	rm = new ResultMaster();
        	ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
        	rm.setResult(result);
        }
		 
		return rm;
	}
	
	@RequestMapping(value = "/furytree", method = RequestMethod.PUT, produces="application/json")
	@ResponseBody
	public ResultMaster updateFurytree(HttpServletRequest request) throws Exception {
		
		ResultMaster rm = null; 

		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();

		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try{
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo( request );
		}catch(Exception e){
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if( requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK) ){
			rm = p1Service.updateFurytree(requestCustomHeaderInfo, param);
        }else{
        	rm = new ResultMaster();
        	ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
        	rm.setResult(result);
        }
		 
		return rm;
	}
	
	@RequestMapping(value = "/furytree", method = RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	public ResultMaster deleteFurytree(HttpServletRequest request) throws Exception {
		
		ResultMaster rm = null; 

		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();

		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try{
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo( request );
		}catch(Exception e){
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if( requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK) ){
			rm = p1Service.deleteFurytree(requestCustomHeaderInfo, param);
        }else{
        	rm = new ResultMaster();
        	ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
        	rm.setResult(result);
        }
		 
		return rm;
	}
	
	@RequestMapping(value = "/nagative", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResultMaster createNagative(HttpServletRequest request) throws Exception {
		
		ResultMaster rm = null; 

		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();

		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try{
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo( request );
		}catch(Exception e){
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if( requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK) ){
			rm = p1Service.createNagative(requestCustomHeaderInfo, param);
        }else{
        	rm = new ResultMaster();
        	ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
        	rm.setResult(result);
        }
		 
		return rm;
	}
	
	@RequestMapping(value = "/nagative", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResultMaster getNagativeList(HttpServletRequest request) throws Exception {
		
		ResultMaster rm = null; 

		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();

		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try{
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo( request );
		}catch(Exception e){
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if( requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK) ){
			rm = p1Service.getNagativeList(requestCustomHeaderInfo, param);
        }else{
        	rm = new ResultMaster();
        	ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
        	rm.setResult(result);
        }
		 
		return rm;
	}
	
	@RequestMapping(value = "/nagative", method = RequestMethod.PUT, produces="application/json")
	@ResponseBody
	public ResultMaster updateNagative(HttpServletRequest request) throws Exception {
		
		ResultMaster rm = null; 

		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();

		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try{
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo( request );
		}catch(Exception e){
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if( requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK) ){
			rm = p1Service.updateNagative(requestCustomHeaderInfo, param);
        }else{
        	rm = new ResultMaster();
        	ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
        	rm.setResult(result);
        }
		 
		return rm;
	}
	
	@RequestMapping(value = "/nagative", method = RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	public ResultMaster deleteNagative(HttpServletRequest request) throws Exception {
		
		ResultMaster rm = null; 

		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();

		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try{
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo( request );
		}catch(Exception e){
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if( requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK) ){
			rm = p1Service.deleteNagative(requestCustomHeaderInfo, param);
        }else{
        	rm = new ResultMaster();
        	ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
        	rm.setResult(result);
        }
		 
		return rm;
	}
	
	@RequestMapping(value = "/alternative", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResultMaster createAlternative(HttpServletRequest request) throws Exception {
		
		ResultMaster rm = null; 

		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();

		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try{
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo( request );
		}catch(Exception e){
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if( requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK) ){
			rm = p1Service.createAlternative(requestCustomHeaderInfo, param);
        }else{
        	rm = new ResultMaster();
        	ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
        	rm.setResult(result);
        }
		 
		return rm;
	}
	
	@RequestMapping(value = "/alternative", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResultMaster getAlternativeList(HttpServletRequest request) throws Exception {
		
		ResultMaster rm = null; 

		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();

		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try{
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo( request );
		}catch(Exception e){
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if( requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK) ){
			rm = p1Service.getAlternativeList(requestCustomHeaderInfo, param);
        }else{
        	rm = new ResultMaster();
        	ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
        	rm.setResult(result);
        }
		 
		return rm;
	}
	
	@RequestMapping(value = "/alternative", method = RequestMethod.PUT, produces="application/json")
	@ResponseBody
	public ResultMaster updateAlternative(HttpServletRequest request) throws Exception {
		
		ResultMaster rm = null; 

		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();

		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try{
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo( request );
		}catch(Exception e){
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if( requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK) ){
			rm = p1Service.updateAlternative(requestCustomHeaderInfo, param);
        }else{
        	rm = new ResultMaster();
        	ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
        	rm.setResult(result);
        }
		 
		return rm;
	}
	
	@RequestMapping(value = "/alternative", method = RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	public ResultMaster deleteAlternative(HttpServletRequest request) throws Exception {
		
		ResultMaster rm = null; 

		HttpRequest httpRequest = new HttpRequest(request);
		Map<String, Object> param = httpRequest.getParameterNameAndValueMap();

		//@ Session check
		RequestCustomHeaderInfo requestCustomHeaderInfo = null;
		try{
			requestCustomHeaderInfo = getSessionRequestCustomHeaderInfo( request );
		}catch(Exception e){
			e.printStackTrace();
			requestCustomHeaderInfo = new RequestCustomHeaderInfo();
			requestCustomHeaderInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
		}
		
		if( requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK) ){
			rm = p1Service.deleteAlternative(requestCustomHeaderInfo, param);
        }else{
        	rm = new ResultMaster();
        	ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
        	rm.setResult(result);
        }
		 
		return rm;
	}
}
