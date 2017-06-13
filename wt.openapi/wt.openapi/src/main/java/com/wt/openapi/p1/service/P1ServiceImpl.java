package com.wt.openapi.p1.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.openapi.common.model.RequestCustomHeaderInfo;
import com.wt.openapi.common.model.ResultCode;
import com.wt.openapi.common.model.ResultMaster;
import com.wt.openapi.p1.dao.P1Dao;
import com.wt.openapi.p1.model.AlternativeListVo;
import com.wt.openapi.p1.model.AlternativeVo;
import com.wt.openapi.p1.model.EmotionListVo;
import com.wt.openapi.p1.model.EmotionVo;
import com.wt.openapi.p1.model.FurytreeListVo;
import com.wt.openapi.p1.model.FurytreeVo;
import com.wt.openapi.p1.model.NagativeListVo;
import com.wt.openapi.p1.model.NagativeVo;
import com.wt.openapi.utils.CodeMessage;
import com.wt.openapi.utils.StringUtils;


@Service
public class P1ServiceImpl implements P1Service{
	
	Log logger = LogFactory.getLog(getClass());
	
	@Autowired
 	private P1Dao p1Dao;

    @Override
	public ResultMaster createEmotion(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
    	ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", Integer.parseInt(memNo));
		
		String emValue = StringUtils.nvlStr(param.get("emValue"));
		String emCase = StringUtils.nvlStr(param.get("emCase"));
		if ("".equals(emValue) || "".equals(emCase)) {
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NOT_PARAMETER); //필수 파라메터가 없습니다.
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			param.put("emValue", Integer.parseInt(emValue));
			param.put("emCase", Integer.parseInt(emCase));
			param.put("emContent", param.get("emContent"));
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			int result = p1Dao.createEmotion(param);
			
			if (result > 0) {
				CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			} else {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_DATA_INSER_FAIL); 
				rm.setResult(resultCode);
				return rm;
			}
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
			rm.setResult(resultCode);
			return rm;
		}
		
		rm.setResult(resultCode);
		
		return rm;
	}

	@Override
	public ResultMaster getEmotionList(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
		ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		EmotionListVo data = new EmotionListVo();
		List<EmotionVo> emotionList = null;
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", memNo);
		
		try {
			int totalCount = p1Dao.getEmotionListCount(param);
			if(totalCount < 1) {
				logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_NO_DATA));
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NO_DATA);
				rm.setResult(resultCode);
				return rm;
			}
			
			data.setTotalCount("" + totalCount);
			
			emotionList = p1Dao.getEmotionList(param);
			if(!emotionList.isEmpty()){
				data.setEmList(emotionList);
			}
			
			CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			
		} catch (Exception e) {
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
	public ResultMaster updateEomtion(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
		ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", memNo);
		
		String emNo = StringUtils.nvlStr(param.get("emNo"));
		if ("".equals(emNo)) {
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NOT_PARAMETER); //필수 파라메터가 없습니다.
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			param.put("emNo", Integer.parseInt(emNo));
			
			if (!StringUtils.nvlStr(param.get("emValue")).equals("")) {
				param.put("emValue", Integer.parseInt(param.get("emValue").toString()));
			}
			
			if (!StringUtils.nvlStr(param.get("emCase")).equals("")) {
				param.put("emCase", Integer.parseInt(param.get("emCase").toString()));
			}
			
			if (!StringUtils.nvlStr(param.get("emContent")).equals("")) {
				param.put("emContent", param.get("emContent"));
			}
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			
			int result = p1Dao.updateEmotion(param);
			
			if (result > 0) {
				CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			} else {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_DATA_UPDATE_FAIL);
				rm.setResult(resultCode);
				return rm;
			}
			
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
			rm.setResult(resultCode);
			return rm;
		}
		
		rm.setResult(resultCode);
		
		return rm;
	}

	@Override
	public ResultMaster deleteEomtion(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
		ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", memNo);
		
		String emNo = StringUtils.nvlStr(param.get("emNo"));
		if ("".equals(emNo)) {
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NOT_PARAMETER); //필수 파라메터가 없습니다.
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			param.put("emNo", Integer.parseInt(emNo));
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		
		try {
			
			int result = p1Dao.deleteEmotion(param);
			
			if (result > 0) {
				CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			} else {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_DATA_DELETE_FAIL); //필수 파라메터가 없습니다.
				rm.setResult(resultCode);
				return rm;
			}
			
			CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
			rm.setResult(resultCode);
			return rm;
		}
		
		rm.setResult(resultCode);
		
		return rm;
	}
	
	@Override
	public ResultMaster createFurytree(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
    	ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", Integer.parseInt(memNo));
		
		String ftX = StringUtils.nvlStr(param.get("ftX"));
		String ftY = StringUtils.nvlStr(param.get("ftY"));
		if ("".equals(ftX) || "".equals(ftY)) {
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NOT_PARAMETER); //필수 파라메터가 없습니다.
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			param.put("ftX", Integer.parseInt(ftX));
			param.put("ftY", Integer.parseInt(ftY));
			param.put("ftContent", param.get("ftContent"));
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			int result = p1Dao.createFurytree(param);
			
			if (result > 0) {
				CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			} else {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_DATA_INSER_FAIL); 
				rm.setResult(resultCode);
				return rm;
			}
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
			rm.setResult(resultCode);
			return rm;
		}
		
		rm.setResult(resultCode);
		
		return rm;
	}

	@Override
	public ResultMaster getFurytreeList(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
		ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		FurytreeListVo data = new FurytreeListVo();
		List<FurytreeVo> furytreeList = null;
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", memNo);
		
		try {
			int totalCount = p1Dao.getFurytreeListCount(param);
			if(totalCount < 1) {
				logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_NO_DATA));
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NO_DATA);
				rm.setResult(resultCode);
				return rm;
			}
			
			data.setTotalCount("" + totalCount);
			
			furytreeList = p1Dao.getFurytreeList(param);
			if(!furytreeList.isEmpty()){
				data.setFtList(furytreeList);
			}
			
			CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			
		} catch (Exception e) {
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
	public ResultMaster updateFurytree(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
		ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", memNo);
		
		String ftNo = StringUtils.nvlStr(param.get("ftNo"));
		if ("".equals(ftNo)) {
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NOT_PARAMETER); //필수 파라메터가 없습니다.
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			param.put("ftNo", Integer.parseInt(ftNo));
			
			if (!StringUtils.nvlStr(param.get("ftX")).equals("")) {
				param.put("ftX", Integer.parseInt(param.get("ftX").toString()));
			}
			
			if (!StringUtils.nvlStr(param.get("ftY")).equals("")) {
				param.put("ftY", Integer.parseInt(param.get("ftY").toString()));
			}
			
			if (!StringUtils.nvlStr(param.get("ftContent")).equals("")) {
				param.put("ftContent", param.get("ftContent"));
			}
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			
			int result = p1Dao.updateFurytree(param);
			
			if (result > 0) {
				CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			} else {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_DATA_UPDATE_FAIL);
				rm.setResult(resultCode);
				return rm;
			}
			
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
			rm.setResult(resultCode);
			return rm;
		}
		
		rm.setResult(resultCode);
		
		return rm;
	}

	@Override
	public ResultMaster deleteFurytree(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
		ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", memNo);
		
		String ftNo = StringUtils.nvlStr(param.get("ftNo"));
		if ("".equals(ftNo)) {
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NOT_PARAMETER); //필수 파라메터가 없습니다.
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			param.put("ftNo", Integer.parseInt(ftNo));
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		
		try {
			
			int result = p1Dao.deleteFurytree(param);
			
			if (result > 0) {
				CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			} else {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_DATA_DELETE_FAIL); //필수 파라메터가 없습니다.
				rm.setResult(resultCode);
				return rm;
			}
			
			CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
			rm.setResult(resultCode);
			return rm;
		}
		
		rm.setResult(resultCode);
		
		return rm;
	}
	
	@Override
	public ResultMaster createNagative(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
    	ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", Integer.parseInt(memNo));
		
		String ntTitle = StringUtils.nvlStr(param.get("ntTitle"));
		String ntContent = StringUtils.nvlStr(param.get("ntContent"));
		if ("".equals(ntTitle) || "".equals(ntContent)) {
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NOT_PARAMETER); //필수 파라메터가 없습니다.
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			param.put("ntTitle", param.get("ntTitle"));
			param.put("ntContent", param.get("ntContent"));
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			int result = p1Dao.createNagative(param);
			
			if (result > 0) {
				CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			} else {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_DATA_INSER_FAIL); 
				rm.setResult(resultCode);
				return rm;
			}
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
			rm.setResult(resultCode);
			return rm;
		}
		
		rm.setResult(resultCode);
		
		return rm;
	}

	@Override
	public ResultMaster getNagativeList(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
		ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		NagativeListVo data = new NagativeListVo();
		List<NagativeVo> nagativeList = null;
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", memNo);
		
		try {
			int totalCount = p1Dao.getNagativeListCount(param);
			if(totalCount < 1) {
				logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_NO_DATA));
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NO_DATA);
				rm.setResult(resultCode);
				return rm;
			}
			
			data.setTotalCount("" + totalCount);
			
			nagativeList = p1Dao.getNagativeList(param);
			if(!nagativeList.isEmpty()){
				data.setNtList(nagativeList);
			}
			
			CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			
		} catch (Exception e) {
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
	public ResultMaster updateNagative(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
		ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", memNo);
		
		String ntNo = StringUtils.nvlStr(param.get("ntNo"));
		if ("".equals(ntNo)) {
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NOT_PARAMETER); //필수 파라메터가 없습니다.
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			param.put("ntNo", Integer.parseInt(ntNo));
			
			if (!StringUtils.nvlStr(param.get("ntTitle")).equals("")) {
				param.put("ntTitle", param.get("ntTitle"));
			}
			
			if (!StringUtils.nvlStr(param.get("ntContent")).equals("")) {
				param.put("ntContent", param.get("ntContent"));
			}
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			
			int result = p1Dao.updateNagative(param);
			
			if (result > 0) {
				CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			} else {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_DATA_UPDATE_FAIL);
				rm.setResult(resultCode);
				return rm;
			}
			
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
			rm.setResult(resultCode);
			return rm;
		}
		
		rm.setResult(resultCode);
		
		return rm;
	}

	@Override
	public ResultMaster deleteNagative(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
		ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", memNo);
		
		String ntNo = StringUtils.nvlStr(param.get("ntNo"));
		if ("".equals(ntNo)) {
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NOT_PARAMETER); //필수 파라메터가 없습니다.
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			param.put("ntNo", Integer.parseInt(ntNo));
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		
		try {
			
			int result = p1Dao.deleteNagative(param);
			
			if (result > 0) {
				CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			} else {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_DATA_DELETE_FAIL); //필수 파라메터가 없습니다.
				rm.setResult(resultCode);
				return rm;
			}
			
			CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
			rm.setResult(resultCode);
			return rm;
		}
		
		rm.setResult(resultCode);
		
		return rm;
	}
	
	@Override
	public ResultMaster createAlternative(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
    	ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", Integer.parseInt(memNo));
		
		String anTitle = StringUtils.nvlStr(param.get("anTitle"));
		String anContent = StringUtils.nvlStr(param.get("anContent"));
		if ("".equals(anTitle) || "".equals(anContent)) {
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NOT_PARAMETER); //필수 파라메터가 없습니다.
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			param.put("anTitle", param.get("anTitle"));
			param.put("anContent", param.get("anContent"));
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			int result = p1Dao.createAlternative(param);
			
			if (result > 0) {
				CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			} else {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_DATA_INSER_FAIL); 
				rm.setResult(resultCode);
				return rm;
			}
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
			rm.setResult(resultCode);
			return rm;
		}
		
		rm.setResult(resultCode);
		
		return rm;
	}

	@Override
	public ResultMaster getAlternativeList(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
		ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		AlternativeListVo data = new AlternativeListVo();
		List<AlternativeVo> alternativeList = null;
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", memNo);
		
		try {
			int totalCount = p1Dao.getAlternativeListCount(param);
			if(totalCount < 1) {
				logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_NO_DATA));
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NO_DATA);
				rm.setResult(resultCode);
				return rm;
			}
			
			data.setTotalCount("" + totalCount);
			
			alternativeList = p1Dao.getAlternativeList(param);
			if(!alternativeList.isEmpty()){
				data.setAnList(alternativeList);
			}
			
			CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			
		} catch (Exception e) {
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
	public ResultMaster updateAlternative(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
		ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", memNo);
		
		String anNo = StringUtils.nvlStr(param.get("anNo"));
		if ("".equals(anNo)) {
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NOT_PARAMETER); //필수 파라메터가 없습니다.
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			param.put("anNo", Integer.parseInt(anNo));
			
			if (!StringUtils.nvlStr(param.get("anTitle")).equals("")) {
				param.put("anTitle", param.get("anTitle"));
			}
			
			if (!StringUtils.nvlStr(param.get("anContent")).equals("")) {
				param.put("anContent", param.get("anContent"));
			}
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			
			int result = p1Dao.updateAlternative(param);
			
			if (result > 0) {
				CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			} else {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_DATA_UPDATE_FAIL);
				rm.setResult(resultCode);
				return rm;
			}
			
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
			rm.setResult(resultCode);
			return rm;
		}
		
		rm.setResult(resultCode);
		
		return rm;
	}

	@Override
	public ResultMaster deleteAlternative(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
		ResultMaster rm = new ResultMaster();
		ResultCode resultCode = new ResultCode();
		
		String memNo = requestCustomHeaderInfo.getMemNo();
		param.put("memNo", memNo);
		
		String anNo = StringUtils.nvlStr(param.get("anNo"));
		if ("".equals(anNo)) {
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_NOT_PARAMETER); //필수 파라메터가 없습니다.
			rm.setResult(resultCode);
			return rm;
		}
		
		try {
			param.put("anNo", Integer.parseInt(anNo));
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_USE_NO_PARAMETER);
			rm.setResult(resultCode);
			return rm;
		}
		
		
		try {
			
			int result = p1Dao.deleteAlternative(param);
			
			if (result > 0) {
				CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			} else {
				CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_DATA_DELETE_FAIL); //필수 파라메터가 없습니다.
				rm.setResult(resultCode);
				return rm;
			}
			
			CodeMessage.setResultCode(resultCode,	CodeMessage.RESPONSE_CODE_OK);
			
		} catch (Exception e) {
			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
			rm.setResult(resultCode);
			return rm;
		}
		
		rm.setResult(resultCode);
		
		return rm;
	}
	
	
	
	 

//	public ResultMaster deleteEomtion(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param) {
//		ResultMaster rm = new ResultMaster();
//		ResultCode resultCode = new ResultCode();
//		
//		String memNo = requestCustomHeaderInfo.getMemNo();
//		param.put("memNo", memNo);
//		
//		try {
//			
//		} catch (Exception e) {
//			logger.error(CodeMessage.getResultMsg(CodeMessage.RESPONSE_CODE_SERVICE_ERROR), e);
//			CodeMessage.setResultCode(resultCode, CodeMessage.RESPONSE_CODE_SERVICE_ERROR);
//		}
//		
//		rm.setResult(resultCode);
//		
//		return rm;
//	}
    
    
    
}