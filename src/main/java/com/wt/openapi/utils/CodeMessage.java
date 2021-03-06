package com.wt.openapi.utils;

import java.io.UnsupportedEncodingException;

import com.wt.openapi.common.configuration.ConfigProperties;
import com.wt.openapi.common.model.ResultCode;

public class CodeMessage {
	
	/**
	 * 
	 */
	/** 접근 경로 : 통과 */	
	public final static String POC_ID_INERNAL = "0";
	
	/** 접근 경로 : Project1(실증연동 정신건강 App) */
	public final static String POC_ID_P1 = "1";
	
	/** 접근 경로 : Project2(실증연동 운동량 App) */
	public final static String POC_ID_P2 = "2";
	
	/** 접근 경로 : Project3(실증연동 정신건강 App) */
	public final static String POC_ID_P3 = "3";
	
	/** 접근 경로 : Project4(실증연동 다이어트 App) */
	public final static String POC_ID_P4 = "4";
	
	/** 회원 상태 : 이메일 인증(정회원) */
	public final static String USER_STATUS_AUTH = "0";
	
	/** 회원 상태 : 이메일 미인증 */
	public final static String USER_STATUS_NOT_AUTH = "1";
	
	/** 회원 상태 : 탈퇴 회원 */
	public final static String USER_STATUS_NOT_WITHDRAW = "2";
	
	/**
	 * OK
	 */
	public static String RESPONSE_CODE_OK = "200";
	public static String RESPONSE_CODE_OK_CHANGE_NOT_CONTENTS = "200104";
	
	/**
	 * 400
	 */	
	/** 필수 파라메터가 없습니다. */
	public static String RESPONSE_CODE_NOT_PARAMETER = "400101";
	
	/**허용되지 않는 파라메터 값이 있습니다. */
	public static String RESPONSE_CODE_USE_NO_PARAMETER = "400102";
	
	/**회원정보가 존재하지 않습니다. */
	public static String RESPONSE_CODE_NOT_MEMBER = "400103";
	
	/** 세션 아이디가 유효하지 않습니다. */
	public static String RESPONSE_CODE_NO_SESSION = "400104";
	
	/**데이터가 존재하지 않습니다. */
	public static String RESPONSE_CODE_NO_DATA = "400105";
	
	/**요청 JSON 값에 문제가 있습니다. */
	public static String RESPONSE_CODE_WRONG_JSON = "400106";
	
	/** 해당 파일이 존재하지 않습니다. */
	public static String RESPONSE_CODE_NO_FILE = "400201";
			
	/**허용되지 않는 메서드 방식으로 호출하였습니다. */	
	public static String RESPONSE_CODE_BAD_METHOD = "405201";
	
	/**필수 헤더 정보가 존재하지 않습니다. */
	public static String RESPONSE_CODE_NO_HEADER = "412101";
	
	/**허용되지 않는 Content Type으로 요청하였습니다. */
	public static String RESPONSE_CODE_BAD_CONTENT_TYPE = "415201";
	
	/**허용되지 않는 Media Type으로 요청하였습니다. */
	public static String RESPONSE_CODE_BAD_MEDIA_TYPE = "415202";
	
	/**허용되지 않는 Sort Type으로 요청하였습니다. */
	public static String RESPONSE_CODE_BAD_SORT_TYPE = "415203";
	
	/**허용되지 않는 Doc Type으로 요청하였습니다. */
	public static String RESPONSE_CODE_BAD_DOC_TYPE = "415204";
	
	/**허용되지 않는 searchKey Type으로 요청하였습니다. */
	public static String RESPONSE_CODE_BAD_SEARCHKEY_TYPE = "415205";
	
	/**해당 파라메터에 중복된 데이터가 존재합니다. */
	public static String RESPONSE_CODE_OVERLAP_DATA = "416100";
	
	/**해당 회원의 잠금 컨텐츠가 30개를 채웠습니다. 더 이상 컨텐츠를 잠글 */
	public static String RESPONSE_CODE_LOCK_MAX_CONTENTS = "416200";
	
	/** 중복된 앨범 아이디가 존재합니다. */
	public static String RESPONSE_CODE_OVERLAP_ALBUMID = "416101";
	
	/** 중복된 앨범명이 존재합니다. */
	public static String RESPONSE_CODE_OVERLAP_ALBUMNAME = "416102";
	
	/**허용되지 않는 앨범명으로 요청하였습니다. */
	public static String RESPONSE_CODE_NOT_ALLOWD_ALBUMNAME = "416103";
	
	/////////////////////////////
	/** 회원 가입 실패 */
	public static String RESPONSE_CODE_MEMBER_JOIN_FAIL = "420001";
	
	/** 인증회원 아님 */
	public static String RESPONSE_CODE_MEMBER_NOT_AUTH = "420002";
	
	/** 회원정보를 찾을 수 없음 */
	public static String RESPONSE_CODE_MEMBER_SEARCH_FAIL = "420003";
	
	/** 로그인 실패 */
	public static String RESPONSE_CODE_MEMBER_LOGIN_FAIL = "420004";
	
	/** 세션 삭제 실패 */
	public static String RESPONSE_CODE_SESSION_DELETE_FAIL = "420005";
	
	/** 이메일 인증 실패 */
	public static String RESPONSE_CODE_EMAIL_AUTH_FAIL = "420006";
	
	/** 중복된 아이디가 존재 */
	public static String RESPONSE_CODE_OVERLAP_ID = "420007";
	
	/** 비밀번호 변경 실패 */
	public static String RESPONSE_CODE_MEMBER_PASSWORD_MODITY_FAIL = "420008";
	
	/** 비밀번호 변경 실패 */
	public static String RESPONSE_CODE_MEMBER_PASSWORD_NOT_EQUAL = "420009";
	
	/** 비밀번호 변경 실패 */
	public static String RESPONSE_CODE_MEMBER_TEMP_PASSWORD_FAIL = "420010";
	
	/** 중복된 아이디가 존재 */
	public static String RESPONSE_CODE_OVERLAP_NICKNAME = "420011";
	
	/** 아이디 미입력 */
	public static String RESPONSE_CODE_NO_ID = "420012";		//20160907 추가
	
	/** 패스워드 미입력 */
	public static String RESPONSE_CODE_NO_PASSWORD = "420013";	//20160907 추가
	
	/**
	 * 500
	 */
	/** 내부 시스템에서 예기치 않은 오류가 발생하였습니다. */
	public static String RESPONSE_CODE_SERVICE_ERROR = "500301";	
	
	/** 내부 시스템에서 예기치 않은 오류가 발생하였습니다.-인터페이스 ERROR*/
	public static String RESPONSE_CODE_SERVICE_INTERFACE_FAIL = "500302";
	
	/** 해당 클리핑 컨텐츠에 오류가 있습니다. */
	public static String RESPONSE_CODE_CLIP_DATA_ERROR = "500303";
	
	/** worker 등록에 실패했습니다. (MQ Publish 실패). */
	public static String RESPONSE_CODE_WORKER_PUBLISH_FAIL = "500304";
	
	/** worker 등록에 실패했습니다. */
	public static String RESPONSE_CODE_WORKER_INSERT_FAIL = "500305";
	
	/** 서비스 연결에 실패 하였습니다. */
	public static String RESPONSE_CODE_SERVICE_FAIL = "502301";
	
	/**  공유 URL 생성에 실패 하였습니다. */
	public static String RESPONSE_CODE_SHARE_CREATE_FAIL = "502302";
	
	/** 공유 URL 삭제에 실패 하였습니다. */
	public static String RESPONSE_CODE_SHARE_DELETE_FAIL = "502303";
	
	/** 요청된 주소정보 처리 중~ 일부분에 오류가 발생 하였습니다. */
	public static String RESPONSE_CODE_REGION_PROCESS_INCOMPLETE = "502304";
	
	/** META_DISUSE_LAST_UPDATED 에 insert실패  */
	public static String RESPONSE_CODE_INSERT_DISUSE_LAST_UPDATED_FAIL = "502305";
	
	/** 시스템 점검 중입니다. */
	public static String RESPONSE_CODE_SERVICE_STOP = "503999";
	
	
	/** DB ERROR */
	public static String RESPONSE_CODE_DATA_INSER_FAIL = "504001";
	public static String RESPONSE_CODE_DATA_UPDATE_FAIL = "504002";
	public static String RESPONSE_CODE_DATA_DELETE_FAIL = "504003";
	
	
	/**
	 * v3.0 Message Code
	 */
	/** 만료된 Token */
	public final static String RESPONSE_CODE_EXPIRED_TOKEN = "2910";
	
	/** 요청 수행 실패 (기타 오류) */
	public final static String RESPONSE_CODE_FAIL = "999";
	
	/**
	 * message.properties 에서 코드로 메시지를 가져와 String Reutrn 한다.
	 * @param code
	 * @return
	 */
	public static String getResultMsg(String code){
		String msg = ConfigProperties.getProperty("response.code.message."+code);
		
		try {
			msg = new String(msg.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
	
	/**
	 * message.properties 에서 코드로 메시지를 가져온다.
	 * @param result
	 * @param code
	 */
	public static void setResultCode(ResultCode result, String code){
		String msg = ConfigProperties.getProperty("response.code.message."+code);
		
		try {
			msg = new String(msg.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		result.setCode(code);
		result.setMessage(msg);
	}
	
	/**
	 * message.properties 에서 코드로 메시지를 가져온다.
	 * @param code
	 * @return
	 */
	public static ResultCode setResultCode(String code){
		ResultCode result = new ResultCode();
		
		String msg = ConfigProperties.getProperty("response.code.message."+code);
		
		try {
			msg = new String(msg.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		result.setCode(code);
		result.setMessage(msg);
		
		return result;
	}
}
