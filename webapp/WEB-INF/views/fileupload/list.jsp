<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<style>
.card {
	border: 1px solid black;
	float: left;
	margin-right: 5px;
	margin-bottom: 5px;
}

.cardImg {
	width: 100%;
	max-width: 128px;
	vertical-align: middle"
}
</style>

<title>Mysite</title>
</head>
<body>

	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

		<div id="wrapper">
			<div id="content">
	
				<div id="gallery">
					<c:if test="${sessionScope.authUser != null }">
						<form method="post" action="${pageContext.request.contextPath }/gallary/insert" enctype="multipart/form-data">
							<input type="text" name="user_no" value="${sessionScope.authUser.no}">
							<table>
								<tr>
									<td>첨부파일</td>
									<td><input type="file" name="file"></td>
									<td><input type="submit" value="파일업로드"></td>
								</tr>
							</table>
						</form>
					</c:if>

					<ul id="gallarylist">
						<c:forEach items="${fileList }" var="fileVO">
							<li>
								<div>
									<img src="${pageContext.request.contextPath }/upload/${fileVO.saveName}" data-delno="${fileVO.no}">
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>



			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>
	<!-- /container -->

	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="gallarySelect">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" id="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">사진</h4>
				</div>
			
				<div class="modal-body">
					<img id="modalimg" width="500" height="300"><br>
					 <c:if test="${sessionScope.authUser !=null}">
					 	<label>비밀번호</label> 
					 	<input type="password" name="modalPassword" id="modalPassword"><br> 
						 <input type="hidden" name="modalPassword" value="" id="modalNo"><br>
					</c:if>
					<div id="modalmsg"></div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal" id="btn_cancel">취소</button>
					<c:if test="${sessionScope.authUser != null}">
						<button type="button" class="btn btn-danger" id="btn_del">삭제</button>
					</c:if>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/bootstrap.min.js"></script>
<script type="text/javascript">

	$("#gallarylist").on("click","img",function(){
		var $path = $(this).attr("src")
			,$delno = $(this).data("delno");
		$("#modalNo").val($delno);
		$("#modalimg").attr("src",$path)
					  .css("margin-bottom","10px");
		$("#gallarySelect").modal();
	});
	
</script>
</html>

