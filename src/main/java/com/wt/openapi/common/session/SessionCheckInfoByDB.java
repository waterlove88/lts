
package com.wt.openapi.common.session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.openapi.common.configuration.ConfigProperties;
import com.wt.openapi.common.dao.SessionDao;
import com.wt.openapi.common.model.RequestCustomHeaderInfo;
import com.wt.openapi.member.dao.MemberDao;
import com.wt.openapi.member.model.vo.MemberInfo;
import com.wt.openapi.utils.CodeMessage;
import com.wt.openapi.utils.EncryptionUtils;

@Service
public class SessionCheckInfoByDB {
	
	protected Log logger = LogFactory.getLog(getClass());
	EncryptionUtils encryptionUtils = new EncryptionUtils();
	private int sessionTimeout;
	
	@Autowired
	SessionDao sessionDao;
	
	@Autowired
	MemberDao memberDao;
	
	public SessionCheckInfoByDB() {
		sessionTimeout = Integer.parseInt(ConfigProperties.getProperty("sessioncheck.time")) * 60;
	}
	
	public RequestCustomHeaderInfo getSessionCheckInfo(String sessionid) {
		RequestCustomHeaderInfo requestCustomHeaderInfo = new RequestCustomHeaderInfo();
		try {
			requestCustomHeaderInfo = sessionDao.getUserInfoBySession(sessionid);
			if (requestCustomHeaderInfo != null)
				logger.debug(" *getSessionCheckInfo* " + requestCustomHeaderInfo.toString());
			else 
				return new RequestCustomHeaderInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return requestCustomHeaderInfo;
	}
	
	/*
	 * 세션 아이디 생성
	 * 생성 규칙 : sesseionId(기본 스트링) + 회원번호 + 회원ID + 현재 시간 sha256 암호화
	 * 만료시간은 3시간
	 */
	public RequestCustomHeaderInfo makeSessionId(MemberInfo memberInfo, RequestCustomHeaderInfo requestNotLoginHeaderInfo) throws Exception {
		String sessionId = encryptionUtils.getSHA256Digest("sesseionId"+memberInfo.getMemNo()+memberInfo.getMemEmail()+System.currentTimeMillis());
		
		RequestCustomHeaderInfo sessionInfo = new RequestCustomHeaderInfo();
		
		sessionInfo.setMemNo(memberInfo.getMemNo());
		sessionInfo.setSessionid(sessionId);
		sessionInfo.setUserAgent(requestNotLoginHeaderInfo.getUserAgent());
		sessionInfo.setPocId(requestNotLoginHeaderInfo.getPocId());
		sessionInfo.setUserIp(requestNotLoginHeaderInfo.getUserIp());
		sessionInfo.setUserId(memberInfo.getMemEmail());
		sessionInfo.setUserMdn(requestNotLoginHeaderInfo.getUserMdn());
		sessionInfo.setUserHsmodel(requestNotLoginHeaderInfo.getUserHsmodel());
				
		if(sessionDao.insertSessionId(sessionInfo) > 0) {
			sessionInfo.setErrorCode(CodeMessage.RESPONSE_CODE_OK);
			return sessionInfo;
		} else {
			sessionInfo.setErrorCode(CodeMessage.RESPONSE_CODE_NO_SESSION);
			return null;
		}
	}
	
	/*
	 * 세션 정보 삭제
	 */
	public int deleteSessionId(String sessionId) throws Exception {
		return sessionDao.deleteSessionId(sessionId);
	}

	
//	public synchronized boolean deleteSessionCheckInfo(RequestCustomHeaderInfo requestCustomHeaderInfo) {
//		boolean isSuccess = false;
//		String key = ConfigProperties.getProperty("sessioncheck.prefix") + requestCustomHeaderInfo.getTcdSessionid();
//		Jedis jedis = null;
//
//		try {
//			jedis = sentinelJedisPool.getResource();
//			long deleteCount = jedis.del(key);
//			logger.debug("jedis.del(" + key + ") : " + deleteCount);
//
//			isSuccess = true; // Exception만 없다면 성공으로 간주
//		} catch (JedisException e) {
//			e.printStackTrace();
//		} finally {
//			if (jedis != null) {
//				returnJedisResource(jedis);
//			}
//		}
//
//		return isSuccess;
//	}


//	public synchronized String getSessionKey(String key) {
//		String value = null;
//		key = ConfigProperties.getProperty("smsAuth.prefix") + key;
//		Jedis jedis = null;
//
//		try {
//			jedis = sentinelJedisPool.getResource();
//			value = jedis.get(key);
//		} catch (JedisException e) {
//			e.printStackTrace();
//		} finally {
//			if (jedis != null) {
//				returnJedisResource(jedis);
//			}
//		}
//
//		return value;
//	}
//
//	public synchronized void putSessionKey(String key, CheckSmsAuthKeyInfo value) {
//		key = ConfigProperties.getProperty("smsAuth.prefix") + key;
//		Jedis jedis = null;
//
//		try {
//			jedis = sentinelJedisPool.getResource();
////			jedis.set(key, value.getValue());
////			jedis.expire(key, StringUtils.isInt(ConfigProperties.getProperty("smsAuth.timeout.seconds")));
//			jedis.setex(key, StringUtils.isInt(ConfigProperties.getProperty("smsAuth.timeout.seconds")), value.getValue());
//		} catch (JedisException e) {
//			e.printStackTrace();
//		} finally {
//			if (jedis != null) {
//				returnJedisResource(jedis);
//			}
//		}
//	}

}
