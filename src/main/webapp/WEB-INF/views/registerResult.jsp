<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WELL TEC</title>
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<script type="text/javascript" src="/resources/js/jquery-3.1.0.min.js"></script>
	<script type="text/javascript" src="/resources/js/jquery-migrate-1.4.1.min.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
	<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
	
</head>
<body>

<div data-role="page" >
	<div data-role="header">
		<h1>WellTec 회원가입 완료</h1>
	</div>
	<form name="completefrm" id="completefrm" action="login">
	<input type="hidden" name="done_url" value="${done_url }">
	<div data-role="main" class="ui-content" style="margin-left:20px; margin-right:20px">
		<p>회원가입을 완료하기 위해 이메일 인증이 필요합니다.</p>
		<p>가입시 기재한 이메일로 인증을 해주세요.</p>
		<input type="submit" id="okBtn" value="확인">
	</div>
	</form>
</div>
</body>
<script>
$("#okbtn").submit(function(){
	console.log(done_url);
	var done_url = document.login.done_url.value;
	if(done_url == "") {
		done_url = "kakaotalk://launch";
	}
	location.href="/openapi/mobile/login?done_url=" + done_url;
});
</script>
</html>