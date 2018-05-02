<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
			
		<div id="wrapper">
			<div id="content">
				<div id="user">
	
					<form id="join-form" name="joinForm" method="get" action="${pageContext.request.contextPath }/user/join">
					
						<label class="block-label" for="name">이름</label>
						<input id="name" name="name" type="text" value="">
	
						<label class="block-label" for="email">이메일</label>
						<input id="email" name="email" type="text" value="">
						<input type="button" id="btn" value="id 중복체크">
						<input type="hidden" id="isIdCheck" value="false">
						<div id="msg"></div>
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value="">
						
						<fieldset>
							<legend>성별</legend>
							<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
							<label>남</label> <input type="radio" name="gender" value="male">
						</fieldset>
						
						<fieldset>
							<legend>약관동의</legend>
							<input id="agree" type="checkbox" name="agreeProv" value="y">
							<label>서비스 약관에 동의합니다.</label>
							<div id="msg1"></div>
						</fieldset>
						
						<input type="submit" id="submit" value="가입하기">
						
					</form>
					
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div> <!-- /container -->

</body>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.12.4.js"></script>
	<script type="text/javascript">
		$("#submit").on("click", function(){
			var $agree = $("#agree").is(":checked");
			var $isIdCheck = $("#isIdCheck").val();
			if($agree == true && $isIdCheck == "true"){
				return true;
			} else{
				$("#msg1").html("모든 내용을 입력해주세요");
				return false;
			}
		});
	
		$("#btn").on("click",function(){
			var email = $("[name=email]").val();
			
			$.ajax({
				url: "${pageContext.request.contextPath}/user/emailcheck", // 보내려는 page 일단 우리는 controller에 메소드를 만들어 거기로 보냄
				type: "POST",          // 보내는 방식 get/post
				data: {email : email}, // 보낼 데이터를 key:value 형태로 작성 
				dataType: "json", // text, html, xml, json, jsonp, and script.
				success: function(result){
					console.log(result);
					if(result == true){
						$("#isIdCheck").val("true");
						$("#msg").html("사용할 수 있는 아이디 입니다.");
					} else{
						$("#msg").html("사용할 수 없는 아이디 입니다.");
					}
				}, // 성공 시 실행될 함수
				error:function(XHR, status, error){
					console.error(status+" : "+ error);
				} // 실패 시 실행될 함수
			});
		});
		// 데이터를 주고 받을 때 사용한다 또한 비동기식 방식
		// 실행이 되면 그대로 끝
	</script>
</html>