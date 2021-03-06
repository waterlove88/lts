package com.wt.openapi.common.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wt.openapi.common.model.RequestCustomHeaderInfo;

@Repository
public class SessionDaoImpl implements SessionDao {
	
	public static String NAMESPACE = "wt.member.";
	
	@Autowired
    private SqlSession sqlSession;
 
    public void setSqlSession(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }
    
	@Override
	public RequestCustomHeaderInfo getUserInfoBySession(String sessionId) {
		return sqlSession.selectOne(NAMESPACE + "selectUserInfoBySession", sessionId);
	}

	@Override
	public int deleteSessionId(String sessionId) {
		return sqlSession.delete(NAMESPACE + "deleteSessionId", sessionId);
	}

	@Override
	public int insertSessionId(RequestCustomHeaderInfo sessionInfo) {
		return sqlSession.insert(NAMESPACE + "insertSessionId", sessionInfo);
	}
	
}
