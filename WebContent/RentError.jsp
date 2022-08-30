<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			  	<h5 class="card-title">대여 실패</h5>
			 	<p class="card-text">도서를 대여하는 데 실패했습니다.<br>이미 대여된 도서 또는 존재하지 않는 도서 ID를 입력하신 것은 아닌지 확인해주십시오.
			 		<br>회원님이 입력하신 도서ID는 <span class="text-primary">${bookID}</span> 입니다.
			 	</p><br>
			 	<p class="card-text"><small class="text-muted"><a href="rentBook">되돌아가기</a></small></p>
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