<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- /header -->
<div id="header">
	<a href="/mysite/main"><h1>MySite</h1></a>
	<ul>
		<c:if test="${sessionScope.authUser == null}">
			<!-- 로그인 전 -->
			<li><a href="/spring_mysite/loginform">로그인</a></li>
			<li><a href="/spring_mysite/registform">회원가입</a></li>
		</c:if>
		
		<c:if test="${sessionScope.authUser != null }">
			<!-- 로그인 후 -->
			<li><a href="/spring_mysite/modifyform">회원정보수정</a></li>
			<li><a href="/spring_mysite/logout">로그아웃</a></li>
			<li>${sessionScope.authUser.name }님</li>
		</c:if>
	</ul>
</div>