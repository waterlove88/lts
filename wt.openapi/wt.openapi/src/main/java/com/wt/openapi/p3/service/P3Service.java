package com.wt.openapi.p3.service;

import java.util.Map;

import com.wt.openapi.common.model.RequestCustomHeaderInfo;
import com.wt.openapi.common.model.ResultMaster;

public interface P3Service {
	ResultMaster createSleep(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	ResultMaster getSleepList(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	ResultMaster updateSleep(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
	ResultMaster deleteSleep(RequestCustomHeaderInfo requestCustomHeaderInfo, Map<String, Object> param);
}
