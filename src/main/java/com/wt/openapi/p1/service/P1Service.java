package com.wt.openapi.p1.service;

import java.util.Map;

import com.wt.openapi.common.model.RequestCustomHeaderInfo;
import com.wt.openapi.common.model.ResultMaster;

public interface P1Service {
	ResultMaster createEmotion(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	ResultMaster getEmotionList(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	ResultMaster updateEomtion(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	ResultMaster deleteEomtion(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	
	ResultMaster createFurytree(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	ResultMaster getFurytreeList(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	ResultMaster updateFurytree(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	ResultMaster deleteFurytree(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	
	ResultMaster createNagative(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	ResultMaster getNagativeList(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	ResultMaster updateNagative(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	ResultMaster deleteNagative(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	
	ResultMaster createAlternative(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	ResultMaster getAlternativeList(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	ResultMaster updateAlternative(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	ResultMaster deleteAlternative(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	
	
}
