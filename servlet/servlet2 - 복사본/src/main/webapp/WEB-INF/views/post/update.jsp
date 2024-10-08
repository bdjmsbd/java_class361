<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시글 수정</title>

<jsp:include page="/WEB-INF/views/common/head.jsp" />
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<meta charset="UTF-8">
<style>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<div class="container" style="min-height: calc(100vh - 237px)">
		<div class="container">
			<h1>게시글 수정</h1>
			<form action="<c:url value="/post/update"/>" method="post">
				<div class="form-group">
					<input type="text"
						class="form-control mt-3" id="title" name="title" value="${post.po_title}">
				</div>
				<div class="form-group">
					<textarea class="form-control mt-3" id="content" 
					name="content">${post.po_content}</textarea>
				</div>

				<button type="submit" class="btn btn-primary btn-lg col-12">수정 등록</button>
				<input type="hidden" name="po_num" value="${post.po_num}">




			</form>
		</div>

	</div>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script>
	
		$('#content').summernote({
			tabsize : 2,
			height : 450
		});
		
	</script>

</body>
</html>