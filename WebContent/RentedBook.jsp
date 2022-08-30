<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
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
	<div class="p-1 mt-3 ml-3 bg-dark text-white text-center rounded-pill" style="max-width: 20%">
		<h3 class="mt-1 mb-0">대여된 도서</h3>	
	</div>
	<section>
	<c:set var="doneLoop" value="false" />
	<c:forEach var="cnt" begin="0" end="${BookList.listSize -1}" varStatus="status">
		<c:if test ="${not doneLoop}">
			<c:if test="${status.index == 4}">
					<c:set var="doneLoop" value="true" />
			</c:if>	
			<div class="card mt-5 mx-auto" style="max-width: 70%">
	  			<div class="card-header bg-dark text-white">
	   				<b>도서 ID:${BookList.bookID[cnt]}</b>
	 			</div>
	  			<div class="card-body bg-light">
	    			<h5 class="card-title"><i class="fas fa-book">&nbsp;</i>${BookList.bookName[cnt]}&nbsp;<sub>${BookList.authorName[cnt]}</sub></h5>
	   				<p class="card-text">가격: ${BookList.price[cnt]} 원</p>
	  			</div>
	  			<div class="card-footer">
	    			대여 가능 : <span class="text-danger">NO</span>
	    			| 대여 횟수: <b>${BookList.rentCount[cnt]}</b>
	  			</div>
			</div>
		</c:if>
	</c:forEach>
	</section>
		    <ul class= "pagination justify-content-center mt-3">
	    	<li class="page-item">
				<c:set var="pageNumber" value="${PageNumber}" />
				<c:if test="${pageNumer < 0 or pageNumber == 0}">
					<a class="page-link disabled">이전</a>
				</c:if>
				<c:if test="${pageNumber > 0}">
					<a class = "page-link" href="./rentedBook?pageNumber=${pageNumber - 1}">이전</a>
				</c:if>
	    	</li>
	    	<li>
				<c:set var="size" value="${BookList.listSize}" />
				<c:if test="${size < 6}">
					<a class="page-link disabled">다음</a>
				</c:if>
				<c:if test="${size == 6 or size > 6}">
					<a class = "page-link" href="./rentedBook?pageNumber=${pageNubmer + 1}">다음</a>
				</c:if>
  			</li>
	    </ul>
	<footer class="bg-dark mt-4 p-4 text-center" style="color: #FFFFFF;">
    	Copyright &copy; 2020 유진영 All Rights Reserved.
    </footer>
	<script src="./js/jquery.min.js"></script>
	<script src="./js/popper.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>