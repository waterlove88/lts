package com.wt.openapi.member.dao;

import java.util.HashMap;

import com.wt.openapi.member.model.info.EmailAuthInfo;
import com.wt.openapi.member.model.vo.MemberInfo;
import com.wt.openapi.member.model.vo.MemberPwdInfo;

public interface MemberDao {
	int joinMember(MemberInfo memberInfo) throws Exception;
	int insertPwd(MemberPwdInfo memberPwdInfo) throws Exception;
	MemberInfo selectMember(HashMap<String, Object> param) throws Exception;
	MemberPwdInfo selectPwd(String memNo) throws Exception;
	int updateEmailAuth(EmailAuthInfo emailAuthInfo) throws Exception;
	int checkId(String userId) throws Exception;
	int modifyPwd(MemberPwdInfo newPwdInfo) throws Exception;
	int checkNickName(String userNickName);
}
