<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	body {background:#fff !important;}
	table{border:1px solid #000; border-collapse: collapse; width:100%;} 
	table th{background-color:#bfbfbf; font-weight:bold;}
	table th,td{height:30px; padding:6px 2px 6px 6px;}
</style>
</head>
<body>
<form id="frm" name="frm" method="post">
	<input type="hidden" name="done_url" value="${ done_url }" />
	<input type="hidden" name="reg_from" value="${ reg_from }" />
	<input type="hidden" name="ostype" value="${ ostype }" />
	<input type="hidden" id="isagree" name="isagree" value="" />
</form>

<div id="tcloudapp" class="front_wrap" style="display:block;">
	<header class="memberheader">
		<h1 class="hide">TCloud 모바일 웹</h1>
		<button type="button" class="btn_prev" onclick="backToNC();">이전</button>
		<h2 class="page-tit" style="text-align:center; font-size: 18px; display: block;">Tcloud 로그인</h2>
	</header>
	<div class="midarea login" style="min-height: 516px;">
		<h1 class="logo">T cloud</h1>
		<article class="tcloudloginTOI">
			<dl>				
				<dd class="inp_id"><input type="text" id="loginId" name="loginId" onkeyup="fncSetButtonStatus2()" class="txt" placeholder="T cloud ID" value="" style="color:#000;"/></dd>
				<dd class="inp_pw"><input type="password" id="pw" name="pw" class="txt" placeholder="비밀번호" value="" style="color:#000;" /></dd>
				<dd><button class="btn_login non-active" id="btn_login" type="button" onclick="fnSumbit()">로그인</button></dd>								
			</dl>						
		</article>
	</div>
</div>
<div id="tcloudapp" class="nc_agreement_detail" style="display:none;">
	<header class="memberheader">
		<h1 class="hide">TCloud 모바일 웹</h1>
		<button type="button" class="btn_prev" onclick="backToLogin();">이전</button>
		<h2 class="page-tit" style="text-align:center; font-size: 18px; display: block;">개인정보 제 3자 제공동의</h2>
	</header>
	<div class="midarea">
	</div>	
</div>
</body>
<script>

//메뉴 열기
var menuNotiLoginObj = {
		url : "/forapp/member/notiPopup_nc01.do",
		title : "NC 인증 메뉴",
		id : "notiNcLoginPopup",
		modal : true,	
		radius : 15
};

// 메뉴 닫기
function callbackFncNotiLoginPopup(){
	$('body').unbind('touchmove');
	Popup.close(menuNotiLoginObj);
	$("#pw").val('');
	$("#chkbox_agree").removeClass("on");
};

function fncOpenNotiLoginPopup(notiType) {
	
	$('body').bind('touchmove', function(e){
		e.preventDefault();
	});

	
	var width = 290;
	var height = 162;
	/* if(notiType == "2200"){
		height = 176;
	} */
	if(notiType == "0003" || notiType == "0099" || notiType == "7033" || notiType == "7023"){
		height = 182;
	}	
	if(notiType == "7211"){
		height = 222;
	}
	if(notiType == "2222" || notiType == "2115"){
		height = 202;
	}
	
	var param = "notiType="+notiType;
	
	menuNotiLoginObj.width = width;
	menuNotiLoginObj.height = height;
	menuNotiLoginObj.param = param;
	
	Popup.open(menuNotiLoginObj);
}

$(document).ready(function() {
	
	$("#chkbox_agree").removeClass("on");
	$("#isagree").val("");
	
	$("#chkbox_agree").click(function(){
		if($("#isagree").val() == ""){
			$("#chkbox_agree").addClass("on");
			$("#isagree").val("1");	
		}else{
			$("#chkbox_agree").removeClass("on");
			$("#isagree").val("");	
		}
	});	
	
	$("#btn_agree").click(function(){
		if($("#isagree").val() == ""){
			$("#chkbox_agree").addClass("on");
			$("#isagree").val("1");	
		}
		$(".front_wrap").attr("style", "display:block");
		$(".nc_agreement_detail").attr("style", "display:none");
	});
	
	var loginIdDecrypt = '${ loginIdDecrypt }';
	
	if(loginIdDecrypt != ''){
		
		$("#loginId").val(loginIdDecrypt);
		$("#loginId").attr("readonly",true);
		$("#chkbox_agree").addClass("on");
		$("#isagree").val("1");
		$(".agreechk").attr("style", "display:none");
		$("#btn_login").removeClass("non-active").addClass("active");
	}
	
	$("#loginId").bind('paste',function(e){
		$("#btn_login").removeClass("non-active").addClass("active");
	});
	
	if ($("#loginId").val()!=''){
		$("#btn_login").removeClass("non-active").addClass("active");
	}
});

function fnSumbit(){
	
	var done_url = document.frm.done_url.value;
	if (done_url == "") {
		done_url = "newcloud://loginFromTC";
	}
	
	var loginId = $("#loginId").val();
	var pw = $("#pw").val();
	
	if(loginId == ""){
		//alert("아이디를 확인해 주세요.");
		fncOpenNotiLoginPopup('0001');
		return;
	}else if(pw == ""){
		//alert("비밀번호를 확인해 주세요.");
		fncOpenNotiLoginPopup('0002');
		return;
	}else if($("#isagree").val() == ""){
		//alert("개인정보 제 3자 제공동의가 필요합니다. 확인 후 다시 로그인 해주세요.");
		fncOpenNotiLoginPopup('0003');
		return;
	}
	
	var str = $("#loginId").val().trim();
	
	var han = /[\u3131-\u314e|\u314f-\u3163|\uac00-\ud7a3]/g;
	var chk_han = str.match(han);

	if(chk_han) {
		fncOpenNotiLoginPopup('2200');
		return false;
	}
	
	$.ajax({
			url : "/openapi/member/login",
			data : {
				loginId : loginId,
				pw : pw
			},
			dataType : "json",
			success: function(data){
				var resultCode = data.resultCode;
				var tcdsessionid = data.tcdsessionid;
				var memNo = data.memNo;
				var loginId = data.loginId;
				var totalAmount = data.totalAmount;
				
				if(resultCode == "200"){
					if(tcdsessionid != "" && memNo != ""){
						location.href=done_url+"?resultCode=200&tcdsessionid="+tcdsessionid+"&memNo="+memNo+"&regtype=0&loginId="+loginId+"&totalAmount="+totalAmount;	
					}else{
						fncOpenNotiLoginPopup('0099');	
					}								
				}else if (resultCode == "2115" ){
					//alert('이메일 미인증 상태입니다. 가입하신 One ID 계정에 접속하셔서 <br /> 이메일 인증 완료 후 로그인 해 주시기 바랍니다.');
					fncOpenNotiLoginPopup('2115');
				}else if (resultCode == "7033" ){
					//alert('이메일 미인증 상태입니다. 자세한 내용은 고객센터에 문의 바랍니다.');
					fncOpenNotiLoginPopup('7033');
				}else if(resultCode == "2200" || resultCode == "2801"){
					//alert('ID 또는 비밀번호를 다시 확인해 주세요.');
					fncOpenNotiLoginPopup('2200');
				}else if(resultCode == "2201" ) {
	 				//alert('ID 또는 비밀번호를 다시 확인해 주세요.');
	 				fncOpenNotiLoginPopup('2201');
				}else if(resultCode == "7023" || resultCode == "5500" || resultCode == "5002"){	
	 				//alert('서버접속이 지연되고 있습니다. 잠시 후 이용해 주시기 바랍니다.');
	 				fncOpenNotiLoginPopup('7023');
				}else if(resultCode == "2222"){	
					//alert("로그인 5회 실패로 잠금 처리되었습니다. T cloud에서 잠금 해제 후 다시 로그인 해주세요.");
					fncOpenNotiLoginPopup('2222');
				}else if(resultCode == "7211"){ 				
	 				//alert("암호 입력 10회 오류로 인해 잠금 설정 되었습니다. 잠금 해제 및 암호 관련 안내는 고객센터 ‘1800-0151’로 문의 바랍니다.");
	 				fncOpenNotiLoginPopup('7211');
				}else{
					//alert('로그인에 실패 했습니다. 잠시 후 이용해 주시기 바랍니다. intent url :: newcloud://loginFromTC?resultCode=500');
					fncOpenNotiLoginPopup('0099');
				}						
			},
			error: function(xhr, ajaxOptions, thrownError){			
				//alert('로그인에 실패 했습니다. 잠시 후 이용해 주시기 바랍니다. intent url :: newcloud://loginFromTC?resultCode=500');
				if(xhr.status == 0){
					location.reload();
	            }else{
                	fncOpenNotiLoginPopup('0099');
	         	}
		 	} 	    	
		});	
}

function backToLogin(){
	$(".front_wrap").attr("style", "display:block");
	$(".nc_agreement_detail").attr("style", "display:none");
}

function backToNC(){
	var done_url = document.frm.done_url.value;
	if (done_url == "") {
		done_url = "newcloud://loginFromTC";
	}
	location.href=done_url+"?resultCode=300&regtype=0";
}

function fncSetButtonStatus2(){
	
	var loginId = $("#loginId").val().trim();
	if(loginId == ""){
		$("#btn_login").removeClass("active").addClass("non-active");
	}else{
		$("#btn_login").removeClass("non-active").addClass("active");
	}
}

</script>
</html>