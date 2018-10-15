package com.wt.openapi.p3.dao;

import java.util.List;
import java.util.Map;

import com.wt.openapi.p3.model.SleepVo;

public interface P3Dao {
	public int createSleep(Map<String, Object> param);
	public List<SleepVo> getSleepList(Map<String, Object> param);
	public int getSleepListCount(Map<String, Object> param);
	public int updateSleep(Map<String, Object> param);
	public int deleteSleep(Map<String, Object> param);
}
