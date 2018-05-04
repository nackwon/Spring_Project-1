<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="navigation">
	<ul>
		<li><a href="${pageContext.request.contextPath }/profile">지미원</a></li>
		<li><a href="${pageContext.request.contextPath }/guest/guestform">방명록</a></li>
		<li><a href="${pageContext.request.contextPath }/guest/gb/ajaxform">AJAX 방명록</a></li>
		<li><a href="${pageContext.request.contextPath }/board/boardList">게시판</a></li>
		<li><a href="${pageContext.request.contextPath }/gallary/list">갤러리</a></li>
	</ul>
</div>
<!-- /navigation -->