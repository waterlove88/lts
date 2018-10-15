package com.wt.openapi.mWeb.contorller;

import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wt.openapi.common.controller.GeneralController;
import com.wt.openapi.common.model.RequestCustomHeaderInfo;
import com.wt.openapi.common.model.ResultCode;
import com.wt.openapi.common.model.ResultMaster;
import com.wt.openapi.member.model.info.JoinMemberInfo;
import com.wt.openapi.member.model.info.LoginMemberInfo;
import com.wt.openapi.member.service.MemberService;
import com.wt.openapi.utils.CodeMessage;
import com.wt.openapi.utils.StringUtils;

@RequestMapping(value ="/openapi/mobile")
@Controller
public class mWebController extends GeneralController
{
	@Autowired
	MemberService memberService;
		
	/*
	 * 로그인 화면
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String done_url = ServletRequestUtils.getStringParameter(request, "done_url", "");
		
		ModelAndView mav = new ModelAndView("/Login_itrc");
		
		if("".equals(done_url)){
			done_url = "kakaotalk://launch";
		}
		mav.addObject("done_url", done_url);
		logger.info("login done_url : " + done_url);
		return mav;
	}
	
	
	/*
	 * 모바일 웹 로그인
	 */
	@RequestMapping(value = "/mLogin", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResultMaster mLoginMember(HttpServletRequest request, HttpServletResponse response, @ModelAttribute @Valid LoginMemberInfo loginMemberInfo, BindingResult bindingResult) 
	{
		ResultMaster rm = new ResultMaster();
		
		request.setAttribute("server", "welltec");
		RequestCustomHeaderInfo requestCustomHeaderInfo = geNotLoginRequestHeaderInfo(request);
		if((!requestCustomHeaderInfo.getErrorCode().equals(CodeMessage.RESPONSE_CODE_OK)) || bindingResult.hasErrors())
		{
			ResultCode result = CodeMessage.setResultCode(CodeMessage.RESPONSE_CODE_NOT_PARAMETER);
			rm.setResult(result);
		} else {
			rm = memberService.loginMember(loginMemberInfo, requestCustomHeaderInfo, response);
		}
		
		  /* Enumeration headers = request.getHeaderNames();
		    
		    while(headers.hasMoreElements())    
		    {    
		        String headerName    = (String)headers.nextElement();    
		        String value        = request.getHeader(headerName);    
		        logger.info("key:"+headerName+", value:"+value);
		    } */ 
		logger.info("message : " + rm.getResult().getCode());
		return rm;
	}
	
	@RequestMapping(value = "/notiPopup_01")
	public ModelAndView notiLoginPopup_wt(HttpServletRequest request) throws Exception
	{
		String notiType = StringUtils.nvlStr(request.getParameter("notiType"), "0004");
		logger.info("notiType : " + notiType);
		
		ModelAndView mav = new ModelAndView("/notipopup");
		
		mav.addObject("notiType", notiType);
		
		return mav;
	}
	
	/*
	 * 회원가입 페이지
	 */
	@RequestMapping(value = "/register")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String done_url = ServletRequestUtils.getStringParameter(request, "done_url", "");
		
		ModelAndView mav = new ModelAndView("/memRegister");
		
		if("".equals(done_url)){
			done_url = "kakaotalk://launch";
		}
		logger.info("register done_url : " + done_url);
		mav.addObject("done_url", done_url);
		return mav;
	}
	
	@RequestMapping(value = "/registDB", method = RequestMethod.POST)
	public ModelAndView registDB(HttpServletRequest request, @ModelAttribute @Valid JoinMemberInfo joinMemberInfo)
	{
		ModelAndView mav = new ModelAndView();
		ResultMaster rm = new ResultMaster();
		String done_url = ServletRequestUtils.getStringParameter(request, "done_url", "");
		
	    rm = memberService.joinMember(joinMemberInfo);
		mav.addObject("code", rm.getResult().getCode());
		mav.addObject("message", rm.getResult().getMessage());
		mav.addObject("done_url", done_url);
		mav.setViewName("/registerResult");
		
		logger.info("registDB done_url : " + done_url);
		return mav;
	}
	
	@RequestMapping(value = "/test")
	public String test()
	{
		return "/test";
	}
}


//// 헤더 전체정보 보기
//Enumeration<String> em = request.getHeaderNames();
//
//while(em.hasMoreElements()){
//    String name = em.nextElement() ;
//    String val = request.getHeader(name) ;
//     
//    logger.info("HeaderInfo!!!! : " + name + ", " + val);
//    //out.println(name + " : " + val) ;
//}
