<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="${pageContext.request.contextPath }/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath }/assets/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">

<title>Guest Book List</title>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name" /></td>
							<td>비밀번호</td>
							<td><input type="password" name="password" /></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input class="btn" type="button"
								id="btnAdd" VALUE="확인 " /></td>
						</tr>
					</table>
					<!-- 방명록 리스트 -->
					<ul id="guestList">
					</ul>
					<button class="btn" id="more_btn">더 보기</button>
				</div>
				<!-- /guestbook -->
			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
	<!-- /container -->

	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="del-pop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" id="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label> <input type="password" name="modalPassword"
						id="modalPassword"><br> <input type="hidden"
						name="modalPassword" value="" id="modalNo"> <br>
					<div id="modalmsg"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal"
						id="btn_cancel">취소</button>
					<button type="button" class="btn btn-danger" id="btn_del">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var end = 5,
			start = 1;
		fetchList(start, end);
		
		$(window).on("scroll", function() {
			
			if ($(window).scrollTop() == $(document).height() - $(window).height()) {
				start += 5;
				end += 5;
				
				fetchList(start, end);
			}
		});
	});

	$("#btnAdd").on("click", function() {
		//event.preventDefault(); 기존의 form으 기능을 죽이는 명령어
		
		var $name = $("[name=name]").val()
			,$password = $("[name=password]").val()
			,$content = $("[name=content]").val();
		
		// 자바스크립트에서 객체로 표현하는 방법
		//guestbookVo = { };

		$.ajax({
			url : "${pageContext.request.contextPath }/guest/gb/add",
			type : "POST",
			contentType : "application/json",
			// 이렇게 작성하면 json 형태로 만들어서 보내준다.
			//data : JSON.stringify(guestbookVo),
			data : {
				name : $name,
				password : $password,
				content : $content
			},
			dataType : "json",
			success : function(vo) {
				random(vo, "up");
				$("[name=name]").val("");
				$("[name=password]").val("");
				$("[name=content]").val("");
			},
		});

	});

	function fetchList(start, end) {
		$.ajax({
			url : "${pageContext.request.contextPath }/guest/gb/list",
			type : "POST",
			dataType : "json",
			success : function(list) {
				for (var i = start - 1; i < end; i++) {
					random(list[i], "down");
				}
			}
		});
	}

	function random(guestbookVo, updown) {
		var str = "";
		str = "<li>";
		str += "	<table>";
		str += "		<tr>";
		str += "			<td>[" + guestbookVo.no + "]</td>";
		str += "			<td>" + guestbookVo.name + "</td>";
		str += "			<td>" + guestbookVo.reg_date + "</td>";
		str += "			<td><button id='btnModal' class='btn' data-delno='"+guestbookVo.no+"'>삭제</button></td>";
		str += "		</tr><tr>";
		str += "			<td colspan=4>" + guestbookVo.content + "</td>";
		str += "		</tr>";
		str += "	</table>";
		str += "</li>";

		if (updown == "up") {
			$("#guestList").prepend(str);
		} else if (updown == "down") {
			$("#guestList").append(str);
		} else {
			console.log("오류");
		}
	}

	$("#guestList").on("click", "button", function() {
		var $delno = $(this).data("delno");
		console.log($delno);
		console.log($(this));
		$("#modalNo").val($delno);
		$("#del-pop").modal();
	});

	$("#btn_del").on("click", function() {
		var $modalPassword = $("#modalPassword").val(),
			$modalNo = $("#modalNo").val();
		$.ajax({
			url : "${pageContext.request.contextPath }/guest/gb/del",
			type : "POST",
			data : {
				no : $modalNo,
				password : $modalPassword
			},
			dataType : "json",
			success : function(flag) {
				if (flag == true) {
					$("#btnModal").parents("li").remove();
					$("#modalPassword").val("");
					$("#del-pop").modal("hide");
				} else if (flag == false) {
					$("#modalmsg").html("비밀번호가 틀립니다.");
					$("#modalPassword").val("");
				}
			}
		});
	});

	// 모달 창 취소 시 초기화
	$("#btn_cancel, #close").on("click", function() {
		$("#modalmsg").text("");
	});

	$("#more_btn").on("click", function() {
		var number = 5;
	});

	/* $("#more_btn").on("click", function() {
		start += 5;
		end += 5;
		$("#guestList").append(fetchList(start, end));
	});
 */
	// 스크롤
	/* $(window).on("scroll", function() {
		if ($(window).scrollTop() == $(document).height() - $(window).height()) {
			fetchList();
		}
	}); */
</script>
</html>