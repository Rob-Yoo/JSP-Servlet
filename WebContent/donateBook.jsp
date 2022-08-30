<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="./css/bootstrap.min.css">
	<link rel="stylesheet" href="./css/custom.css">
	<link rel="stylesheet" href="./css/index.css">
<title>Rob's Library</title>
</head>
<body>
	<nav>
    	<jsp:include page="layout_nav.jsp" />	
	</nav>
	<div class="p-1 mt-3 ml-3 bg-dark text-white text-center rounded-pill" style="max-width: 20%">
		<h3 class="mt-1 mb-0">도서 기증하기</h3>	
	</div>
	<section class="mt-5 ml-5 mx-auto" style="max-width: 60%; height: 348px;">
		<div class="card">
  			<div class="card-header bg-dark text-white">
    			<b>${userID} 님의  도서 기증하기</b>
  			</div>
			 <div class="card-body">
		    	<h5 class="card-title">기증할 도서의 정보를 입력해주세요:</h5>
		  		 <div class="card-text">
		  		 	<form action="donateBook" method="post">
    					<div class="form-row"> <!-- 하나의 행에는 12개의 열이 주어지는게 기본값 -->
    						<div class="form-group col-sm-6"> <!-- 2개의 form이므로 6씩 차지하게 설정 -->
    							<label>도서 ID</label>
    							<input type="text" name="bookID" class="form-control" maxlength="5">
    						</div>
    						<div class="form-group col-sm-6">
    							<label>도서명</label>
    							<input type="text" name="bookName" class="form-control" maxlength="20">
    						</div>
    					</div>
    					<div class="form-row">
    						<div class="form-group col-sm-6">
    							<label>저자명</label>
    							<input type="text" name="authorName" class="form-control" maxlength="30">
    						</div>
    						<div class="form-group col-sm-6">
    							<label>가격</label>
    							<input type="text" name="price" class="form-control" maxlength="10">
    						</div>
    					</div>
				    	<button type="submit" class="btn btn-primary">확인</button>
				    	<input type="reset" class="btn btn-secondary" value="취소">
    				</form>
		  		 </div>
		  	</div>
		</div>
	</section>
	<footer class="bg-dark mt-4 p-4 text-center" style="color: #FFFFFF;">
    	Copyright &copy; 2020 유진영 All Rights Reserved.
    </footer>
	<script src="./js/jquery.min.js"></script>
	<script src="./js/popper.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>