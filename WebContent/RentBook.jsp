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
		<h3 class="mt-1 mb-0">도서 대여하기</h3>	
	</div>
	<section class="mt-5 ml-5 mx-auto" style="max-width: 85%; height: 348px;">
		<div class="card">
			<div class="row no-gutters">
	    		<div class="col-md-4">
	      			<img src="freddie-marriage-w8JiSVyjy-8-unsplash2.jpg" class="card-img" alt="이미지를 불러오지 못했습니다.">
	    		</div>
	    		<div class="col-md-8">
				     <div class="card-body">
					    <h5 class="card-title">${userID} 님의 도서 대여하기</h5>
				     	<form action="rentBook" method="post">
				     		<fieldset>
					        <legend class="card-text">대여할 도서의 ID를 입력하세요 </legend>
	    					<label>도서 ID</label>
	    					<input type="text" name="bookID" class="form-control" maxlength="5" 
	    					style="max-width: 50%" value="${bookID}">
	    					<button type="submit" class="btn btn-primary mt-3">확인</button>
				     		</fieldset>
				     	</form>
				     </div>
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