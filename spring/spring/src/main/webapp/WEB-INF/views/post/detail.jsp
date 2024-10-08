<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<c:if test="${post ne null }">
		<h1>게시글 상세</h1>

		<div class="form-group">
			<label>제목:</label>
			<div class="form-control">${post.po_title }</div>
		</div>
		<div class="form-group">
			<label>작성자:</label>
			<div class="form-control">${post.po_me_id }</div>
		</div>
		<div class="form-group">
			<label>작성일:</label>
			<div class="form-control">
				<fmt:formatDate value="${post.po_date }"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</div>
		</div>
		<div class="form-group">
			<label>조회수:</label>
			<div class="form-control">${post.po_view }</div>
		</div>
		<div class="form-group">
			<label for="content">내용:</label>
			<div class="form-control" style="min-height: 400px; height: auto">${post.po_content }</div>
		</div>
		<c:if test="${list.size() != 0 }">
			<div class="form-group">
				<label>첨부파일:</label>
				<c:forEach items="${list }" var="file">
					<a href="<c:url value="/download${file.fi_name}"/>"
						class="form-control" download="${file.fi_ori_name }">${file.fi_ori_name }</a>
				</c:forEach>
			</div>
		</c:if>
	</c:if>
	<c:if test="${post eq null }">
		<h3>삭제되거나 잘못된 게시글입니다.</h3>
	</c:if>

	<c:url var="url" value="/post/list">
		<c:param name="co_num" value="${cri.co_num }" />
		<c:param name="page" value="${cri.page }" />
		<c:param name="type" value="${cri.type }" />
		<c:param name="search" value="${cri.search}" />
	</c:url>
	<div class="mb-2 subbtn-container">
		<a href="${url}" class="btn btn-outline-info">목록</a>

		<c:if test="${post ne null && user.me_id eq post.po_me_id}">
			<div class="float-right">
				<c:url var="url" value="/post/update">
					<c:param name="co_num" value="${cri.co_num }" />
					<c:param name="page" value="${cri.page }" />
					<c:param name="type" value="${cri.type }" />
					<c:param name="search" value="${cri.search}" />
					<c:param name="po_num" value="${post.po_num}" />
				</c:url>

				<a href="${url }" class="btn btn-outline-info mr-1">수정</a>
				<c:url var="url" value="/post/delete">
					<c:param name="co_num" value="${cri.co_num }" />
					<c:param name="page" value="${cri.page }" />
					<c:param name="type" value="${cri.type }" />
					<c:param name="search" value="${cri.search}" />
					<c:param name="po_num" value="${post.po_num}" />
				</c:url>


				<a href="${url }" class="btn btn-outline-info btn-del ">삭제</a>
			</div>
		</c:if>
	</div>
	<hr>
	<div class="comment-container">
		<ul class="comment-list" style="list-style: none; padding: 0">
			<li class="comment-item">
				<div class="clearfix">
					<span class="float-left" style="line-height: 38px">아이디</span>
					<div class="float-right">
						<button class="btn btn-outline-info mr-1">수정</button>
						<button class="btn btn-outline-danger">삭제</button>
					</div>
				</div>

				<div style="padding-left: 20px; line-height: 38px">댓글내용</div>
			</li>
		</ul>

		<div class="comment-pagination">
			<ul class="pagination justify-content-center comment-pm">
				<li class="page-item"><a class="page-link"
					href="javascript:void(0);">Prev</a></li>
				<li class="page-item"><a class="page-link"
					href="javascript:void(0);">1</a></li>
				<li class="page-item"><a class="page-link"
					href="javascript:void(0);">Next</a></li>
			</ul>
		</div>
		<div class="comment-input-box">
			<div class="input-group mb-5">
				<textarea class="form-control" placeholder="댓글 입력"
					id="input-comment"> </textarea>
				<div class="input-group-append">
					<button class="btn input-group-text btn-comment-insert">등록</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		var cri = {
			search : '${post.po_num}',
			page : 1
		}
		/* getCommentList(cri); */
		
	/* 
	pagination.jsp
	1. 댓글 번호를 알아야 삭제할 수 있음
	- 삭제 버튼을 눌렀을 때 댓글 번호를 알아야 함
	  => 삭제 버튼에 댓글번호를 추가
	  => data-xxx="댓글번호"
	2. 삭제 버튼을 클릭 했을 때 댓글 번호를 서버에(컨트롤러) 주면서 삭제하고 삭제 여부를 알려달라고 요청
	  => 삭제할 댓글 번호를 가져옴. $('삭제버튼선택자').data('xxx')
	  => ajax로 댓글 번호를 전송
	  => ajax 성공 시 성공 여부에 따라 알림을 출력하고 댓글을 새로고침
	
	컨트롤러
	1. 댓글 삭제를 위한 메소드를 추가
	  => @RequestMapping or @GetMapping or @PostMapping 중 하나를 선택해서 메소드 위에 추가
	  => @ResponseBody를 추가
	2. 메소드에서 기능을 구현
	  => 화면에서 보낸 정보를 가져옴(댓글 번호)
	  => 서비스에게 삭제할 댓글 번호와 로그인한 사용자 정보를 주면서 삭제하라고 요청하고 결과를 저장
	  => 삭제 성공 여부를 화면에 전달
	서비스
	1. 사용자가 없으면 false를 반환
	2. 댓글을 삭제후 결과를 반환
	다오/매퍼
	1. 댓글을 삭제하는 쿼리를 구현
	*/

		function checkLogin() {
			if (${user == null || user.me_id.equals("")}) {
				return false;
			} 
			return true;
		}
		
		function alertLogin() {
			if (checkLogin()) {
				return false;
			} else {
				if (confirm("로그인이 필요한 서비스입니다.\n로그인 하시겠습니까?")) {
					location.href = "<c:url value="/login"/>";
				}
			}
			return true;
		}

		function getCommentList(cri) {

			$.ajax({
				async : true, //비동기 : true(비동기), false(동기)
				url : '<c:url value="/comment/list"/>',
				type : 'post',
				data : JSON.stringify(cri),
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data) {
					displayCommentList(data.list);
					displayPagenation(data.pm);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR);
				}
			});
		}
		
		function getCommentList2(cri){
			$.ajax({
				async : true, 
				url : '<c:url value="/comment/list2"/>', 
				type : 'post', 
				data : JSON.stringify(cri), 
				contentType : "application/json; charset=utf-8",
				success : function (data){ // return 값 => pagination.jsp
					$('.comment-container').html(data);
				}, 
				error : function(jqXHR, textStatus, errorThrown){
					console.log(jqXHR)
				}
			});
		}

		function displayCommentList(list) {
			if(list == null || list.length == 0) {
				$(".comment-list").html(`
						<li class="comment-item display-4 ">등록된 댓글이 없습니다.</li>`);
				return; 
			}
			var str = ``;
			for(comment of list) {
				var btns = '';
				if(comment.cm_me_id == '${user.me_id}'){
					btns = `
						<div class="float-right">
							<button class="btn btn-outline-info">수정</button>
							<button class="btn-comment-del btn btn-outline-danger" 
								data-num="${comment.cm_num}">삭제</button>
						</div>
					`;
				}
				str += `
					<li class="comment-item">
					<div class="clearfix">
						<span class="float-left" style="line-height: 38px">\${comment.cm_me_id}</span>
						\${btns}
					</div>

					<div style="padding-left: 20px; line-height: 38px">\${comment.cm_content}</div>
				</li>
				`;
			}
			$('.comment-list').html(str);
			
		}
		
		function displayPagenation(pm){
			if(pm == null || pm.endPage == 0) {
				return;
			}
			var str = '';
			
			if(pm.prev) {
			str += `
				<li class="page-item" data-page="\${pm.startPage-1}"><a class="page-link"
				href="javascript:void(0);">Prev</a></li>
			`;
			}
			
			for(var i = pm.startPage; i<= pm.endPage; i++) {
				var active = pm.cri.page == i ? 'active' : ''; 
			str += `
				<li class="page-item \${active}" data-page="\${i}><a class="page-link"
				href="javascript:void(0);">\${i}</a></li>
			`;
			}

			if(pm.next) {
			str += `
				<li class="page-item" data-page="\${pm.endPage+1}><a class="page-link"
				href="javascript:void(0);">Next</a></li>
			`;
			}
			// .comment-pm
			$('.comment-pagination>.pagination').html(str);
		}
		
		getCommentList2(cri);
		
		$(document).on('click', '.comment-pagination .page-item', function(){
			cri.page = $(this).data('page');
			getCommentList2(cri);
		})

		$('.btn-del').click(function() {
			if (confirm('정말 삭제하시겠습니까?'))
				return true;
			else
				return false;
		})

/* 		$('.btn-comment-insert').click(function() { */
		$(document).on('click', '.btn-insert', function(){
			//

			if (alertLogin()) {
				return;
			}

			var cm_content = $('#input-comment').val();
			var cm_po_num = "${post.po_num}";

			var comment = {
				cm_content : cm_content,
				cm_po_num : cm_po_num
			}
			
			if(cm_content.length == 0){
				alert('댓글을 입력하세요');
				$('#input-comment').focus();	
				return;
			}

			$.ajax({
				async : true, //비동기 : true(비동기), false(동기)
				url : '<c:url value ="/comment/insert"/>',
				type : 'post',
				data : JSON.stringify(comment),
				contentType : "application/json; charset=utf-8",
				/* dataType : "json",  */

				success : function(data) {
					if (data) {
						alert('댓글을 등록했습니다.')
						$('#input-comment').val('');
					} else {
						alert('댓글을 등록에 실패했습니다.')
					}
					getCommentList2(cri);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR);
				}
			});
		})
	</script>
</body>
</html>
