 package com.wt.openapi.common.dao;

import com.wt.openapi.common.model.RequestCustomHeaderInfo;

public interface SessionDao {
	RequestCustomHeaderInfo getUserInfoBySession(String sessionId);
	int deleteSessionId(String sessionId);
	int insertSessionId(RequestCustomHeaderInfo sessionInfo);
}
