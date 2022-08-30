<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/custom.css">
<link rel="stylesheet" href="./css/index.css">
<title>도서 대여 시스템</title>
</head>
<body>
	<nav>
		<jsp:include page="./layout_nav.jsp" />
	</nav>
	<section class="container mt-4" style="max-width: 560px;">
    	<div class="card">
    		<div class="card-header">
    			<span><b>회원가입</b></span>
    		</div>
    		<div class="card-body">
    			<form method="post" action="userJoinAction">
    		<div class="form-group">
    			<label>아이디</label>
    			<input type="text" name="userID" class="form-control">
    		</div>
    		<div class="form-group">
    			<label>비밀번호</label>
    			<input type="password" name="userPassword" class="form-control">
    		</div>
    		<div class="form-group">
    			<label>비밀번호 확인</label>
    			<input type="password" name="userPasswordCheck" class="form-control">
    		</div>
    		<button type="submit" class="btn btn-primary">확인</button>
    	</form>
    		</div>
    	</div>
    </section><br>
 	<footer class="bg-dark mt-5 p-4 text-center" style="color: #FFFFFF;">
	   	Copyright &copy; 2020 유진영 All Rights Reserved.
	</footer>
    <script src="./js/jquery.min.js"></script>
	<script src="./js/popper.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>