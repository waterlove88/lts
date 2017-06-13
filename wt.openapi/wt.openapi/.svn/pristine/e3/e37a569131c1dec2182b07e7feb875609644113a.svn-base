package com.wt.openapi.p3.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.openapi.common.model.RequestCustomHeaderInfo;
import com.wt.openapi.common.model.ResultCode;
import com.wt.openapi.common.model.ResultMaster;
import com.wt.openapi.p3.dao.P3Dao;
import com.wt.openapi.p3.model.SleepListVo;
import com.wt.openapi.p3.model.SleepVo;
import com.wt.openapi.utils.CodeMessage;
import com.wt.openapi.utils.StringUtils;

@Service
public class P3ServiceImpl implements P3Service {
	
	Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private P3Dao p3Dao;
	
	@Override
	public ResultMaster createSleep(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param)
	{
		ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", Integer.parseInt(memNo));
		
		String platform = StringUtils.nvlStr(param.get("platform"));
		String model = StringUtils.nvlStr(param.get("model"));
		String sleepefficiency = StringUtils.nvlStr(param.get("sleepefficiency"));
		String duration = StringUtils.nvlStr(param.get("duration"));
		String respiration = StringUtils.nvlStr(param.get("respiration"));
		
		if("".equals(platform) || "".equals(model) || "".equals(sleepefficiency) || 
			"".equals(duration) || "".equals(respiration)) {
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NOT_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			param.put("platform", param.get("platform"));
			param.put("model", param.get("model"));
			param.put("sleepefficiency", Integer.parseInt(sleepefficiency));
			param.put("duration", Integer.parseInt(duration));
			param.put("respiration", Integer.parseInt(respiration));
		} catch(Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			int result = p3Dao.createSleep(param);
			
			if(result > 0) {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_OK);
			} else {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_DATA_INSER_FAIL);
				rm.setResult(resultCode);
				return rm;
			}
		} catch(Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
			rm.setResult(resultCode);
			return rm;
		}
		
		rm.setResult(resultCode);
		return rm;
	}
	
	@Override
	public ResultMaster getSleepList(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param)
	{
		ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		SleepListVo data = new SleepListVo();
		List<SleepVo> sleepList = null;
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", memNo);
		
		try {
			int totalCount = p3Dao.getSleepListCount(param);
			if(totalCount < 1) {
				logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_NO_DATA));
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NO_DATA);
				rm.setResult(resultCode);
				return rm;
			}
			
			data.setTotalCount("" + totalCount);
			
			sleepList = p3Dao.getSleepList(param);
			if(!sleepList.isEmpty()) {
				data.setSlList(sleepList);
			}
			
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_OK);
		} catch(Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
			rm.setResult(resultCode);
			return rm;
		}
		
		rm.setResult(resultCode);
		rm.setBody(data);
		
		return rm;
	}
	
	@Override
	public ResultMaster updateSleep(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param)
	{
		ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", memNo);
		
		String sleepNo = StringUtils.nvlStr(param.get("sleepNo"));
		if("".equals(sleepNo)) {
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NOT_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			param.put("sleepNo", Integer.parseInt(sleepNo));
			
			if(!StringUtils.nvlStr(param.get("platform")).equals("")) {
				param.put("platform", param.get("platform"));
			}
			
			if(!StringUtils.nvlStr(param.get("model")).equals("")) {
				param.put("model", param.get("model"));
			}
			
			if(!StringUtils.nvlStr(param.get("sleepefficiency")).equals("")) {
				param.put("sleepefficiency", Integer.parseInt(param.get("sleepefficiency").toString()));
			}
			
			if(!StringUtils.nvlStr(param.get("duration")).equals("")) {
				param.put("duration", Integer.parseInt(param.get("duration").toString()));
			}
			
			if(!StringUtils.nvlStr(param.get("respiration")).equals("")) {
				param.put("respiration", Integer.parseInt(param.get("respiration").toString()));
			}
		} catch(Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			int result = p3Dao.updateSleep(param);
			
			if(result > 0) {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_OK);
			} else {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_DATA_UPDATE_FAIL);
				rm.setResult(resultCode);
				return rm;
			}
		} catch(Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
			rm.setResult(resultCode);
			return rm;
		}
		
		rm.setResult(resultCode);
		return rm;
	}
	
	@Override
	public ResultMaster deleteSleep(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param)
	{
		ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", memNo);
		
		String sleepNo = StringUtils.nvlStr(param.get("sleepNo"));
		if("".equals(sleepNo)) {
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NOT_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		try { 
			param.put("sleepNo", Integer.parseInt(sleepNo));
		} catch(Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			int result = p3Dao.deleteSleep(param);
			
			if(result > 0) {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_OK);
			} else {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_DATA_DELETE_FAIL);
				rm.setResult(resultCode);
				return rm;
			}
			
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_OK);
		} catch(Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
			rm.setResult(resultCode);
			return rm;
		}
	
		rm.setResult(resultCode);
		return rm;
	}
}
