package com.wt.openapi.p3.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wt.openapi.p3.model.SleepVo;

@Repository
public class P3DaoImpl implements P3Dao {
	
	public static String NAMESPACE = "wt.p3.";
	
	@Autowired
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession)
	{
		this.sqlSession = sqlSession;
	}
	
	@Override
	public int createSleep(Map<String, Object> param) {
		return sqlSession.insert(NAMESPACE + "insertSleep", param);
	}
	
	@Override
	public int getSleepListCount(Map<String, Object> param) {
		return sqlSession.selectOne(NAMESPACE + "selectSleepCount", param);
	}
	
	@Override
	public List<SleepVo> getSleepList(Map<String, Object> param) {
		return sqlSession.selectList(NAMESPACE + "selectSleep", param);
	}
	
	@Override
	public int updateSleep(Map<String, Object> param) { 
		return sqlSession.update(NAMESPACE + "updateSleep", param);
	}
	
	@Override
	public int deleteSleep(Map<String, Object> param) {
		return sqlSession.delete(NAMESPACE + "deleteSleep", param);
	}
}
