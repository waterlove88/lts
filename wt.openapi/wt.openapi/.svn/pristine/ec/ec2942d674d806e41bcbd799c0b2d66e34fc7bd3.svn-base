<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WELL TEC</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0"> 
	<script type="text/javascript" src="/resources/js/jquery-3.1.0.min.js"></script>
	<script type="text/javascript" src="/resources/js/jquery-migrate-1.4.1.min.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
	<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
	
	<style>
	select {
		border-top-left-radius: 0;
		border-top-right-radius: 0;
		border-bottom-left-radius: 0;
		border-bottom-right-radius: 0;
		-webkit-appearance: none;	
	}
	table {
		max-width: 100%;
		height: auto;
	}
		.button {
			background-color:#99cc33!important;
		}
		.ui-btn {
			background: #99cc33;
			text-shadow: none;
		}
		.ui-btn-inner { 
			color: #FFFFFF;
		}
	</style>
</head>
<body>
<div data-role="page">
	<form name="regfrm" id="regfrm" action="registDB" method="post">
	<input type="hidden" name="done_url" value="${done_url }">
	<table id="regTable" style="margin-top:5px; width:100%;">
		<tr valign="top">
			<td colspan="4" align="center">회원가입</td>
		</tr>
		<tr>
		  <td colspan="3"><input type="email" name="userId" id="userId" placeholder="이메일을 입력해주세요"></td>
		  <td><input type="button" id="chkId" value="중복확인" ></td>
		</tr>
		<tr>
			<td colspan="4"><label id="checkId"></label></td>
		</tr>
		<tr>
		  <td colspan="3"><input type="password" name="password" id="memPassword" placeholder="비밀번호를 입력해주세요"></td>
		</tr>
		<tr>
		  <td colspan="4"><label id="availPwd"></label></td>
		</tr>
		<tr>
		  <td colspan="3"><input type="password" name="chkPassword" id="chkPassword" autocomplete="off" placeholder="비밀번호를 한번 더 입력해주세요"></td>
		</tr>
		<tr>
		  <td colspan="4"><label id="checkPwd"></label></td>
		</tr>
		<tr>
		  <td colspan="3"><input type="text" name="name" id="memName" placeholder="이름을 입력해주세요"></td>
		</tr>
		<tr>
			<td></td><td colspan="4"><label id="checkName"></label></td>
		</tr>
		<tr>
		  <td colspan="3"><input type="text" name="nickName" id="nickName" placeholder="닉네임을 입력해주세요" required="" ></td>
		</tr>
		<tr>
		  <td colspan="3"><input type="tel" name="phone" id="phone" placeholder="휴대폰 번호를 입력해주세요" required="" ></td><td>('-' 제외)</td>
		</tr>
		<tr>
			<td align="center">생년월일</td>
			<td sytle="width:90px;">
				<select name="birth_year" id="birth_year">
					<option>0000년</option>
				</select>
			</td>
			<td sytle="width:80px;"> 
				<select name="birth_month" id="birth_month">
					<option>00월</option>
				</select>
			</td>
			<td sytle="width:80px;">
				<select name="birth_day" id="birth_day">
					<option>00일</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="center">몸무게</td>
			<td colspan="2"><input type="text" id="weight" name="weight" autocomplete="off" value=""></td>
			<td>kg</td>
		</tr>
		<tr>
			<td align="center">키</td>
			<td colspan="2"><input type="text" id="height" name="height" autocomplete="off" value=""></td>
			<td>cm</td>
		</tr>
		<tr>
			<td align="center">성별</td>
			<td colspan="3">
				<fieldset data-role="controlgroup" data-type="horizontal">
				    <input type="radio" name="sex" id="memSex" value="M"/>
				    <label for="memSex">남</label>
				    <input type="radio" name="sex" id="memSex2" value="F"/>
				    <label for="memSex2">여</label>
				</fieldset>
			</td>
		</tr>
		<tr>
			<td><input type="checkbox" id="chk1" name="chk1">&nbsp;</td>
			<td colspan="3"><span>만 14세 이상이며, <span style="color: red; font-weight: bold;">서비스 이용약관</span>에<br> 동의합니다.</span></td>

		</tr>
		<tr>
			<td><input type="checkbox" id="chk2" name="chk2">&nbsp;</td>
			<td colspan="3"><span><span style="color: red; font-weight: bold;">개인정보 수집 및 이용</span>에 동의합니다.</span></td>
		</tr>
		<tr align="center">
			<td colspan="2"><input type="submit" value="회원가입"></td>
			<td colspan="2"><input type="button" id="cancelBtn" value="취소"></td>
		</tr>
	</table>
	</form>
</div>
</body>
<script>
	var idcheck = false;
	var pwdcheck = false;
	var id;
$(document).ready(function(){
	$("#chkId").css({'background-color' : 'green'});
	appendYear();
	appendMonth();
	appendDay();
});
	
function appendYear(){
	var date = new Date();
	var year = date.getFullYear();
	var selectValue = document.getElementById("birth_year");
	var optionIndex = 0;

	for(var i=year-100;i<=year;i++){
			selectValue.add(new Option(i+"년",i),optionIndex++);                        
	}
}


function appendMonth(){
	var selectValue = document.getElementById("birth_month"); 
	var optionIndex = 0;

	for(var i=1;i<=12;i++){
			selectValue.add(new Option(i+"월",i),optionIndex++);
	}
}


function appendDay(){
	var selectValue = document.getElementById("birth_day");
	var optionIndex = 0;

	for(var i=1;i<=31;i++){
			selectValue.add(new Option(i+"일",i),optionIndex++);
	}
} 
	$("#regfrm").submit(function() {
		var id2 = $("#userId").val();
		var sex = $('input:radio[name=sex]:checked').val();
		var chk1 = $('input:checkbox[name=chk1]:checked').val();
		var chk2 = $('input:checkbox[name=chk2]:checked').val();
		var birth_year = $('select[name=birth_year]').val();
		var birty_month = $('select[name=birth_month]').val();
		var birth_day = $('select[name=birth_day]').val();
		var weight = $("#weight").val();
		var height = $("#height").val();
		
		if(!idcheck || id2 != id)
		{
			alert('아이디 중복체크를 해주세요');
			return false;
		}
		else if(!pwdcheck)
		{
			alert('비밀번호를 다시 입력해주세요');
			return false;
		}
		else if(birth_year == '년' || birth_month == '월' || birth_day == '일')
		{
			alert('생년월일을 입력해주세요');
			return false;
		}
		else if(typeof sex == "undefined")
		{
			alert('성별을 선택해주세요');
			return false;
		}
		else if(weight == "")
		{
			alert('몸무게를 입력해주세요');
			return false;
		}
		else if(height == "")
		{
			alert('키를 입력해주세요');
			return false;
		}
		else if(typeof chk1 == "undefined")
		{
			alert('서비스 이용약관에 동의해주세요');
			return false;
		}
		else if(typeof chk2 == "undefined")
		{
			alert('개인정보 수집 및 이용에 동의해주세요');
			return false;
		}
		else
		{
			alert('회원가입성공');
		}
		
	});
	
	
	$("#chkId").click(function() {
		var userId = $('#userId').val();
		var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
		id = userId;
		$.ajax({
			url: "/openapi/member/checkId",
			type: "post",
			data: {userId : userId},
			dataType:'json',
			success: function(data){
				var resultCode = data.result;
				if(data.result.code == 200 && regExp.test(userId))
				{
					document.getElementById("checkId").innerHTML = "사용 가능한 아이디입니다.";
					$("#checkId").css("color", "green");
					idcheck=true;
				}
				else if(!regExp.test(userId))
				{
					document.getElementById("checkId").innerHTML = "형식에 맞지 않는 아이디입니다.";	
					$("#checkId").css("color", "blue");
					idcheck=false;
				}
				else
				{
					document.getElementById("checkId").innerHTML = "이미 가입된 아이디입니다.";	
					$("#checkId").css("color", "red");
					idcheck=false;
				}
			},
			error : function(error){
				alert(error.statusText);
			}
		});
		
		return false;
	});
	
	$("#chkPassword").keyup(function(){
		var pwd = $("#memPassword").val();
		var re_pwd = $("#chkPassword").val();
		var pattern1=/[0-9]/;
		var pattern2=/[a-zA-Z]/;
		var pattern3=/[!@#$%^*=-]/;
		if(pwd != re_pwd)
		{
			document.getElementById("checkPwd").innerHTML = "비밀번호가 틀립니다.";
			$("#checkPwd").css("color", "red");
			pwdcheck=false;
		}
		else
		{
			if(!pattern1.test(pwd) || !pattern2.test(pwd) || !pattern3.test(pwd) || pwd.length < 8)
			{
				document.getElementById("availPwd").innerHTML = "비밀번호는 영문, 숫자, 특수기호 8자리 이상으로 구성하여야 합니다.";
				$("#checkPwd").css("color", "blue");
				pwdcheck=false;
			}
			else
			{
				document.getElementById("checkPwd").innerHTML = "비밀번호가 일치합니다.";
				$("#checkPwd").css("color", "green");
				pwdcheck=true;	
			}
			
		}
		
		return false;
	});
	
	$("#memPassword").keyup(function(){
		var pwd = $("#memPassword").val();
		var re_pwd = $("#chkPassword").val();
		var pattern1=/[0-9]/;
		var pattern2=/[a-zA-Z]/;
		var pattern3=/[!@#$%^*=-]/;
		if(pwd != '')
		{
			if(!pattern1.test(pwd) || !pattern2.test(pwd) || !pattern3.test(pwd) || pwd.length < 8)
			{
				document.getElementById("availPwd").innerHTML = "비밀번호는 영문, 숫자, 특수기호 8자리 이상으로 구성하여야 합니다.";
				pwdcheck=false;
			}
			else
			{
				document.getElementById("availPwd").innerHTML = "";
				pwdcheck=false;
			}
			
			if(re_pwd != '')
			{
				if(pwd != re_pwd)
				{
					document.getElementById("checkPwd").innerHTML = "비밀번호가 틀립니다.";
					$("#checkPwd").css("color", "red");
					pwdcheck=false;
				}
				else
				{
					if(!pattern1.test(pwd) || !pattern2.test(pwd) || !pattern3.test(pwd) || pwd.length < 8)
						pwdcheck=true;
					else
					{
						document.getElementById("checkPwd").innerHTML = "비밀번호가 일치합니다.";
						$("#checkPwd").css("color", "green");
						pwdcheck=true;	
					}
				}
			}
		}
		return false;
	});

	$("#cancelBtn").click(function(event){
		if(!confirm("회원가입을 취소하시겠습니까?")) {
			return;
		}
		else {
			event.preventDefault();
			self.location = "/openapi/mobile/login";	
		}
		
	});
	
	$("#okBtn").click(function(event){
		if(!confirm("확인버튼을 누르시면 로그인 페이지로 이동합니다.")) {
			return;
		}
		else {
			event.preventDefault();
			self.location = "/openapi/mobile/login";	
		}
		
	});
<%-- 	
	$.ajax({
	    url : "<%=mwebHostSSL%>/api/member/login.do",
	    data : {
	     loginId : loginId,
	     pw : pw
	    },
	    dataType : "json",
	      success: function(data){
	       var resultCode = data.resultCode;
	       
	       if( resultCode == "200"){
	       }else if ( resultCode == "2115" ){
	        alert('이메일 미인증 상태입니다. 가입하신 One ID 계정에 접속하셔서 이메일 인증 완료 후 로그인 해 주시기 바랍니다.');
	       }else if ( resultCode == "7033" ){
	        alert('이메일 미인증 상태입니다. 자세한 내용은 고객센터에 문의 바랍니다.');
	       }else if(resultCode == '2200' || resultCode == '2201' ) {
	         alert('아이디 또는 비밀번호를 다시 확인하세요.');
	       }else if(resultCode == '7023' || resultCode == '5500' || resultCode == '5002'){ 
	         alert('서버접속이 지연되고 있습니다. 잠시 후 이용해 주시기 바랍니다.');
	       } else if(resultCode == '2222'){ 
	        alert("죄송합니다. \n회원님께서는 로그인 오류 허용횟수를 초과(5회) 하셨습니다.");
	       }else{
	        alert('로그인에 실패 했습니다. 잠시 후 이용해 주시기 바랍니다.');
	       }
	       
	       landingAjax('login', '', resultCode);
	      },
	      error: function(xhr, ajaxOptions, thrownError){
	       landingAjax('login', 'ajaxFail', '');
	       alert('로그인에 실패 했습니다. 잠시 후 이용해 주시기 바랍니다.');
	       }
	           
	   });
 --%>
</script>
</html>
