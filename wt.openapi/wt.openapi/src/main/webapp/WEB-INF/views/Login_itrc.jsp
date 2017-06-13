<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login_ITRC</title>

<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Cache-Control" content="no-cache; no-store; no-save">
<script type="text/javascript" src="/resources/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery-migrate-1.4.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css_login.css">
</head>
<body>

<form name="login" id="login" method="POST" action="mLogin">
	<input type="hidden" name="done_url" value="${done_url }" >
		<table style="width: 100%;" cellspacing="0">
			<tr style="height: 10px;">
				<td style="width: 25%; background-color: #99cc33;"></td>
				<td style="width: 25%; background-color: #006699;"></td>
				<td style="width: 50%; background-color: #33ccff;"></td>
			</tr>
		</table>

		<div class="midarea login" style="min-height: 516px;" style="vertical-align: center;">
		<article class="tcloudloginTOI">
			<dl>
			
				<table class="table_align" style="width: 100%;">
					<tr>
						<td colspan="3">
							<dd align="center">
								<img src="/resources/images/Logo.png"
									style="width: 100%; margin-bottom: 40px;">
							</dd>
						</td>
					</tr>

					<tr style="width: 60%;">
						<td>
							<dd class="inp_id">
								<input type="text" id="userId" name="userId" class="txt" placeholder="ID" autocomplete="off" value=""
									style="color: #000;"/>
							</dd>
						</td>
						<td rowspan="2" style="width: 20%;">
							<dd>
								<button class="btn_login active" id="btn_login" type="button"
									style="margin-left: 5px; width: 90%; height: 90px; margin-top: 5px !important">로그인</button>
							</dd>
						</td>
						<td rowspan="2" style="width: 20%;">
							<dd>
								<button class="btn_login active" id="btn_join" type="button"
									style="margin-left: 5px; width: 90%; height: 90px; margin-top: 5px !important">회원<br>가입
								</button></a>
							</dd>
						</td>
					</tr>

					<tr>
						<td>
							<dd class="inp_pw">
								<input type="password" id="password" name="password" class="txt" placeholder="PW" value="" style="color: #000;" />
							</dd>
						</td>
					</tr>
				</table>

			</dl>
		</article>
		</div>
	</form>

</body>
<script>
var menuNotiLoginObj = {
		url : "/openapi/mobile/notiPopup_01",
		title : "popup",
		id : "notiWtLoginPopup",
		modal : true,
		radius : 15
}

function wtOpenNotiLoginPopup(notiType){
	$('body').bind('touchmove', function(e){
		e.preventDefault();
	});
	
	var width = 290;
	var height = 162;
	
	var param = "notiType="+notiType;
	if(notiType == '420012')
		alert("아이디를 확인해주세요 : " + param);
	
	menuNotiLoginObj.width = width;
	menuNotiLoginObj.height = height;
	menuNotiLoginObj.param = param;
	
	Popup.open(menuNotiLoginObj);
}
	
$("#btn_login").click(function(){
	var done_url = document.login.done_url.value;
	if(done_url == "") {
		done_url = "kakaotalk://launch";
	}
	
	var userId = $("#userId").val();
	var password = $("#password").val();
	
	if(userId == "") {
		alert("아이디를 입력해주세요.");
		//wtOpenNotiLoginPopup('420012');
		return;
	}
	else if(password == ""){
		alert("패스워드를 입력해주세요.");
		//wtOpenNotiLoginPopup('420013');
		return;
	}
	
	var str = $("#userId").val().trim();
	var han = /[\u3131-\u314e|\u314f-\u3163|\uac00-\ud7a3]/g;		//한글체크하는 정규표현식
	
	if(str.match(han)){
		alert('str.match');
		return false;
	}
	
	$.ajax({
		url : "/openapi/mobile/mLogin",
		type : "post",
		data : {
			userId : userId,
			password : password
		},
		dataType : "json",
		success: function(data) {
			var resultCode = data.result.code;
			var memNo = data.body.memNo;
			var Email = data.body.memEmail;
			var nickName = data.body.memNickName;
			var weight = data.body.memWeight;
			var height = data.body.memHeight;

			if(resultCode == "200"){
				location.href=done_url;
			}
			else if(resultCode == "420003") {
				alert("존재하지 않는 회원입니다.");
			}
			else if(resultCode == "400101") {
				alert("비밀번호를 잘못 입력하셨습니다.");
			}
			else if(resultCode == "420009") {
				alert("아이디나 비밀번호가 일치하지 않습니다.");
			}
			else if(resultCode == "420002") { 
				alert("이메일 미인증 상태입니다. 인증 후 로그인해주세요.");
			}
			//alert("resultCode : " + resultCode + ", memNo : " + memNo + ", userId : " + Email + ", nickname : " + nickName + ", weight : " + weight
			//		+ ", height : " + height + ", done_url : " + done_url);
		},
		error: function(xhr, ajaxOptions, thrownError) {
			if(xhr.status == 0) {
				location.reload();
			}
		}
	});
});

$("#btn_join").click(function(){
	var done_url = document.login.done_url.value;
	if(done_url == "") {
		done_url = "kakaotalk://launch";
	}
	location.href="/openapi/mobile/register?done_url=" + done_url;
});
	
</script>
</html>