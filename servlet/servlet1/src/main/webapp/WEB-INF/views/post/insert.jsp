<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>게시글 등록</title>
<jsp:include page="/WEB-INF/views/common/head.jsp" />
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<meta charset="UTF-8">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container">
		<h1>게시글 등록</h1>
		<form action="<c:url value="/post/insert"/>" method="post"
			enctype="multipart/form-data">
			<div class="form-group">
				<label for="title">제목:</label> <input type="text"
					class="form-control" id="title" name="title"
					placeholder='제목을 작성해주세요.'>
			</div>
			<div class="form-group">
				<label for="content">내용:</label>
				<textarea type="text" class="form-control" id="content"
					name="content"></textarea>
			</div>
			<div class="form-group">
				<label>첨부파일(최대 3개)</label> <br>
				<input type="file" class="file-control" name="uploadFiles"> <br>
				<input type="file" class="file-control" name="uploadFiles"> <br>
				<input type="file" class="file-control" name="uploadFiles"> <br>
			</div>
			<button type="submit" class="btn btn-primary btn-lg col-12">등록</button>
			<input type="hidden" name="co_num" value="${co_num}">




		</form>
	</div>

	<script>
		$('#content').summernote({
			placeholder : '게시글을 작성해주세요.',
			tabsize : 2,
			height : 500
		});
	</script>
</body>
</html>