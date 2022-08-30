<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"></head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark"> 
    	<a class="navbar-brand" href="index">도서 대여 시스템</a>
    	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">
    		<span class="navbar-toggler-icon"></span>
    	</button> 
    	<div id="navbar" class="collapse navbar-collapse">
    		<ul class="navbar-nav mr-auto">
    			<li class="nav-item active">
    				<a class="nav-link" href="bookList">도서 목록</a>
    			<li class="nav-item active">
    				<a class="nav-link" href="popularBook">인기 도서</a>
    			<li class="nav-item active">
    				<a class="nav-link" href="rentAble">대여 가능 도서</a>
    			<li class="nav-item active">
    				<a class="nav-link" href="rentedBook">대여된 도서</a>
    			<li class="nav-item active">
    				<a class="nav-link" href="rentBook">도서 대여</a>
    			<li class="nav-item active">
    				<a class="nav-link" href="returnBook">도서 반납</a>
    			<li class="nav-item active">
    				<a class="nav-link" href="donateBook">도서 기증</a>
    		</ul>
    	<c:set var="user" value="${userID}" />
    	<c:if test="${user != null}">
    		<a class="text-white bg-dark" href="userLogoutAction">로그아웃</a>
    	</c:if>
    	<c:if test="${user == null}">
    		<a class="text-white bg-dark" href="./userLogin.jsp">로그인</a>
    		<a class="text-white bg-dark" href="./userJoin.jsp">&nbsp;&nbsp;회원가입</a>
    	</c:if>
 	  	</div>
   	</nav>
    <script src="./js/jquery.min.js"></script>
	<script src="./js/popper.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>