package com.wt.openapi.p1.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wt.openapi.p1.model.AlternativeVo;
import com.wt.openapi.p1.model.EmotionVo;
import com.wt.openapi.p1.model.FurytreeVo;
import com.wt.openapi.p1.model.NagativeVo;

@Repository
public class P1DaoImpl implements P1Dao{
	
	public static String NAMESPACE = "wt.p1.";
	
	@Autowired
    private SqlSession sqlSession;
 
    public void setSqlSession(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }

	@Override
	public int createEmotion(Map<String, Object> param) {
		return sqlSession.insert(NAMESPACE + "insertEmotion", param);
	}

	@Override
	public int getEmotionListCount(Map<String, Object> param) {
		return sqlSession.selectOne(NAMESPACE + "selectEmotionCount", param);
	}

	@Override
	public List<EmotionVo> getEmotionList(Map<String, Object> param) {
		return  sqlSession.selectList(NAMESPACE + "selectEmotion", param);
	}

	@Override
	public int updateEmotion(Map<String, Object> param) {		
		return sqlSession.update(NAMESPACE + "updateEmotion", param);
	}

	@Override
	public int deleteEmotion(Map<String, Object> param) {
		return sqlSession.delete(NAMESPACE + "deleteEmotion", param);
	}
    
	@Override
	public int createFurytree(Map<String, Object> param) {
		return sqlSession.insert(NAMESPACE + "insertFurytree", param);
	}

	@Override
	public int getFurytreeListCount(Map<String, Object> param) {
		return sqlSession.selectOne(NAMESPACE + "selectFurytreeCount", param);
	}

	@Override
	public List<FurytreeVo> getFurytreeList(Map<String, Object> param) {
		return  sqlSession.selectList(NAMESPACE + "selectFurytree", param);
	}

	@Override
	public int updateFurytree(Map<String, Object> param) {		
		return sqlSession.update(NAMESPACE + "updateFurytree", param);
	}

	@Override
	public int deleteFurytree(Map<String, Object> param) {
		return sqlSession.delete(NAMESPACE + "deleteFurytree", param);
	}
	
	@Override
	public int createNagative(Map<String, Object> param) {
		return sqlSession.insert(NAMESPACE + "insertNagative", param);
	}

	@Override
	public int getNagativeListCount(Map<String, Object> param) {
		return sqlSession.selectOne(NAMESPACE + "selectNagativeCount", param);
	}

	@Override
	public List<NagativeVo> getNagativeList(Map<String, Object> param) {
		return  sqlSession.selectList(NAMESPACE + "selectNagative", param);
	}

	@Override
	public int updateNagative(Map<String, Object> param) {		
		return sqlSession.update(NAMESPACE + "updateNagative", param);
	}

	@Override
	public int deleteNagative(Map<String, Object> param) {
		return sqlSession.delete(NAMESPACE + "deleteNagative", param);
	}
	
	@Override
	public int createAlternative(Map<String, Object> param) {
		return sqlSession.insert(NAMESPACE + "insertAlternative", param);
	}

	@Override
	public int getAlternativeListCount(Map<String, Object> param) {
		return sqlSession.selectOne(NAMESPACE + "selectAlternativeCount", param);
	}

	@Override
	public List<AlternativeVo> getAlternativeList(Map<String, Object> param) {
		return  sqlSession.selectList(NAMESPACE + "selectAlternative", param);
	}

	@Override
	public int updateAlternative(Map<String, Object> param) {		
		return sqlSession.update(NAMESPACE + "updateAlternative", param);
	}

	@Override
	public int deleteAlternative(Map<String, Object> param) {
		return sqlSession.delete(NAMESPACE + "deleteAlternative", param);
	}
}