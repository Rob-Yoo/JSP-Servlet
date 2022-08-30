<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<% response.setStatus(200); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="./css/bootstrap.min.css">
	<link rel="stylesheet" href="./css/custom.css">
	<link rel="stylesheet" href="./css/index.css">
	<link rel="stylesheet" href="./css/fontawesome-free-5.12.1-web/fontawesome-free-5.12.1-web/css/all.css">
<title>도서 대여 시스템</title>
</head>
<body>
	<nav>
    	<jsp:include page="layout_nav.jsp" />	
	</nav>
	<section class="mt-5 ml-5 mx-auto" style="max-width: 50%; height: auto;">
		<div class="card mb-3">
		 	<img src="freddie-marriage-w8JiSVyjy-8-unsplash2.jpg" class="card-img-top" alt="This is a BookImage" style="height: 195px;">
		 	<div class="card-body" style="height: 213px;">
			  	<h5 class="card-title">콘텐츠 출력 실패</h5>
			 	<p class="card-text">해당 페이지의 콘텐츠를 출력하는데 실패하였습니다.<br>잘못된 URL로 들어오셨거나 회원님의 반납할 도서목록이 존재하지 않는 경우입니다. 
			 	<br>홈으로 다시 이동해주시거나 도서를 대여해주세요!</p><br>
			 	<p class="card-text"><small class="text-muted"><a href="index">홈으로</a></small></p>
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