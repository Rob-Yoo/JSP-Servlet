<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.PrintWriter"%>
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
		<div class="p-1 mt-3 ml-3 bg-dark text-white text-center rounded-pill" style="max-width: 20%">
			<h3 class="mt-1 mb-0">홈(대여 후기)</h3>	
		</div>
		<section class="container">
    	<form method="get" action="searchAction" class="form-inline mt-3">
    		<select name="bookDivide" class="form-control mx-1 mt-2">
    			<option value="책 제목">책 제목</option>
    			<option value="저자명">저자명</option>
    		</select>
    		<input type="text" name="search" class="form-control mx-1 mt-2" placeholder="내용을 입력하세요">
    		<button type="submit" class="btn btn-primary mx-1 mt-2">검색</button>
    		<a class="btn btn-success mx-1 mt-2" data-toggle="modal" href="#registerModal">등록하기</a>
    	</form>
		<c:set var="doneLoop" value="false" />
		<c:forEach var="cnt" begin="0" end="${EvaluationList.listSize - 1}" varStatus="status">
			<c:if test ="${not doneLoop}">
				<c:if test="${status.index == 4}">
					<c:set var="doneLoop" value="true" />
				</c:if>	
	    		<div class="card bg-light mt-3">
	    			<div class="card-header bg-dark text-white">
		    			<div class="row">
		    				<div class="col-8 text-left">&nbsp;${EvaluationList.bookName[cnt]}&nbsp;<small>${EvaluationList.authorName[cnt]}</small></div>
		    				<div class="col-4 text-right">종합&nbsp;
		    				<c:set var="total" value="${EvaluationList.totalScore[cnt]}" />
		    				<c:if test="${total == '매우 나쁨' or total == '나쁨'}">
		    					<span class="text-danger"><i>${EvaluationList.totalScore[cnt]}</i></span>
		    				</c:if>
		    				<c:if test="${total == '보통'}">
		    					<span class="text-success"><i>${EvaluationList.totalScore[cnt]}</i></span>
		    				</c:if>
		    				<c:if test="${total == '매우 좋음' or total == '좋음'}">
		    					<span class="text-primary"><i>${EvaluationList.totalScore[cnt]}</i></span>
		    				</c:if>
		    				</div>
		    			</div>
		    		</div>
	    		<div class="card-body">
	    			<h5 class="card-title">${EvaluationList.evaluationTitle[cnt]}</h5>
	    			<p class="card-text">${EvaluationList.evaluationContent[cnt]}</p>
	    		</div>
	    		<div class="card-footer">
	    			<div class = "row">
	    				<c:set var="ID" value="${EvaluationList.userID[cnt]}" />
	    				<c:set var="id" value="${userID}" />
	    				<c:if test="${ID == id}">
	    					<div class="col-9 text-left mt-2">
		    					책 수준:&nbsp;<span style="color: #3c40c6;">${EvaluationList.standard[cnt]}</span>
		    					|&nbsp;책 종류:&nbsp;<span style="color: #3c40c6;">${EvaluationList.content[cnt]}</span>
		    					|&nbsp;책 상태:&nbsp;<span style="color: #3c40c6;">${EvaluationList.bookCondition[cnt]}</span>
	    					</div>
	    				</c:if>	    				
	    				<c:if test="${ID != id}">
	    					<div class="col-9 text-left">
		    					책 수준:&nbsp;<span style="color: #3c40c6;">${EvaluationList.standard[cnt]}</span>
		    					|&nbsp;책 종류:&nbsp;<span style="color: #3c40c6;">${EvaluationList.content[cnt]}</span>
		    					|&nbsp;책 상태:&nbsp;<span style="color: #3c40c6;">${EvaluationList.bookCondition[cnt]}</span>
	    					</div>
	    				</c:if>	    				
	    				<c:if test="${ID == id}">
		    				<div class="col-3 text-right">
		    					<form action="deleteAction?evaluationID=${EvaluationList.evaluationID[cnt]}" method ="post">
		    						<a class="btn btn-info" data-toggle="modal" href="#registerModal${EvaluationList.evaluationID[cnt]}">수정</a>
		    						<button type="submit" class="btn btn-danger">삭제</button>
		    					</form>
	    					</div>
	    					<div class="modal fade" id="registerModal${EvaluationList.evaluationID[cnt]}" tabindex="-1" role="dialog" aria-labelledby="modal" 
	    								aria-hidden="true">
						    	<div class="modal-dialog">
						    		<div class="modal-content">
						    			<div class="modal-header">
						    				<h5 class="modal-title" id="modal">평가 등록</h5> 
						    				<button type="button" class="close" data-dismiss="modal" aria-label="Close"> 
						    					<span aria-hidden="true">&times;</span>
						    				</button>
						    			</div>
						    			<div class="modal-body">
						    				<form action="modify?evaluationID=${EvaluationList.evaluationID[cnt]}" method="post">
						    					<div class="form-row"> 
						    						<div class="form-group col-sm-6"> 
						    							<label>책 제목</label>
						    							<input type="text" name="bookName" class="form-control" maxlength="20" 
						    								value="${EvaluationList.bookName[cnt]}">
						    						</div>
						    						<div class="form-group col-sm-6">
						    							<label>저자명</label>
						    							<input type="text" name="authorName" class="form-control" maxlength="20" 
						    									value="${EvaluationList.authorName[cnt]}">
						    						</div>
						    					</div>
						    					<div class="form-group">
						    						<label>제목</label>
						    						<input type="text" name="evaluationTitle" class="form-control" maxlength="30" 
						    								value="${EvaluationList.evaluationTitle[cnt]}">
						    					</div>
						    					<div class="form-group">
						    						<label>내용</label>
						    						<textarea name="evaluationContent" class="form-control" maxlength="2048" style="height: 180px;">
						    								${EvaluationList.evaluationContent[cnt]}</textarea>
						    					</div>
						    					<div class="form-row">
						    						<div class="form-group col-sm-3">
						    							<label>종합</label>
						    							<select name="totalScore" class="form-control">
						    								<option value="매우 좋음">매우 좋음</option>
						    								<option value="좋음">좋음</option>
						    								<option value="보통">보통</option>
						    								<option value="나쁨">나쁨</option>
						    								<option value="매우 나쁨">매우 나쁨</option>
						    							</select>
						    						</div>
						    						<div class="form-group col-sm-3">
						    							<label>책 수준</label>
						    							<select name="standard" class="form-control">
						    								<option value="매우 쉬움" selected>매우 쉬움</option>
						    								<option value="약간 쉬움">약간 쉬움</option>
						    								<option value="보통">보통</option>
						    								<option value="약간 어려움">약간 어려움</option>
						    								<option value="매우 어려움">매우 어려움</option>
						    							</select>
						    						</div>
						    						<div class="form-group col-sm-3">
						    							<label>책 종류</label>
						    							<select name="content" class="form-control">
						    								<option value="학습서" selected>학습서</option>
						    								<option value="소설">소설</option>
						    								<option value="전공 서적">전공 서적</option>
						    								<option value="문학작품">문학작품</option>
						    								<option value="에세이">에세이</option>
						    								<option value="자서전">자서전</option>
						    								<option value="만화">만화</option>
						    								<option value="문제집">문제집</option>
						    							</select>
						    						</div>
						    						<div class="form-group col-sm-3">
						    							<label>책 상태</label>
						    							<select name="bookCondition" class="form-control">
						    								<option value="깔끔" selected>깔끔</option>
						    								<option value="낙서">낙서</option>
						    								<option value="부분 파손">부분 파손</option>
						    								<option value="얼룩짐">얼룩짐</option>
						    							</select>
						    						</div>
						    					</div>
						    					<div class="modal-footer">
						    						<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
						    						<button type="submit" class="btn btn-info">수정하기</button>
						    					</div>
						    				</form>
						    			</div>
						    		</div>
						    	</div>
						    </div>
			    		</c:if>
	    			</div>
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
					<a class = "page-link" href="./index?pageNumber=${pageNumber - 1}">이전</a>
				</c:if>
	    	</li>
	    	<li>
				<c:set var="size" value="${EvaluationList.listSize}" />
				<c:if test="${size < 6}">
					<a class="page-link disabled">다음</a>
				</c:if>
				<c:if test="${size == 6 or size > 6}">
					<a class = "page-link" href="./index?pageNumber=${pageNubmer + 1}">다음</a>
				</c:if>
  			</li>
	    </ul>
		<footer class="bg-dark mt-4 p-4 text-center footer" style="color: #FFFFFF;">
		   	Copyright &copy; 2020 유진영 All Rights Reserved.
		</footer>
		<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true"> <!-- id는 등록하기의 href을 쓰기 위해 -->
	    	<div class="modal-dialog">
	    		<div class="modal-content">
	    			<div class="modal-header">
	    				<h5 class="modal-title" id="modal">평가 등록</h5> <!-- id는 위에 aria-labelledby을 쓰기 위해 -->
	    				<button type="button" class="close" data-dismiss="modal" aria-label="Close"> <!-- data-dismiss는 버튼을 누르면 모달 창이 없어짐 -->
	    					<span aria-hidden="true">&times;</span> <!-- &times;는 닫기버튼 아이콘 -->
	    				</button>
	    			</div>
	    			<div class="modal-body">
	    				<form action="index" method="post">
	    					<div class="form-row"> <!-- 하나의 행에는 12개의 열이 주어지는게 기본값 -->
	    						<div class="form-group col-sm-6"> <!-- 2개의 form이므로 6씩 차지하게 설정 -->
	    							<label>책 제목</label>
	    							<input type="text" name="bookName" class="form-control" maxlength="20">
	    						</div>
	    						<div class="form-group col-sm-6">
	    							<label>저자명</label>
	    							<input type="text" name="authorName" class="form-control" maxlength="20">
	    						</div>
	    					</div>
	    					<div class="form-group">
	    						<label>제목</label>
	    						<input type="text" name="evaluationTitle" class="form-control" maxlength="30">
	    					</div>
	    					<div class="form-group">
	    						<label>내용</label>
	    						<textarea name="evaluationContent" class="form-control" maxlength="2048" style="height: 180px;"></textarea>
	    					</div>
	    					<div class="form-row">
	    						<div class="form-group col-sm-3">
	    							<label>종합</label>
	    							<select name="totalScore" class="form-control">
	    								<option value="매우 좋음" selected>매우 좋음</option>
	    								<option value="좋음">좋음</option>
	    								<option value="보통">보통</option>
	    								<option value="나쁨">나쁨</option>
	    								<option value="매우 나쁨">매우 나쁨</option>
	    							</select>
	    						</div>
	    						<div class="form-group col-sm-3">
	    							<label>책 수준</label>
	    							<select name="standard" class="form-control">
	    								<option value="매우 쉬움" selected>매우 쉬움</option>
	    								<option value="약간 쉬움">약간 쉬움</option>
	    								<option value="보통">보통</option>
	    								<option value="약간 어려움">약간 어려움</option>
	    								<option value="매우 어려움">매우 어려움</option>
	    							</select>
	    						</div>
	    						<div class="form-group col-sm-3">
	    							<label>책 종류</label>
	    							<select name="content" class="form-control">
	    								<option value="학습서" selected>학습서</option>
	    								<option value="소설">소설</option>
	    								<option value="전공 서적">전공 서적</option>
	    								<option value="문학작품">문학작품</option>
	    								<option value="에세이">에세이</option>
	    								<option value="자서전">자서전</option>
	    								<option value="만화">만화</option>
	    								<option value="문제집">문제집</option>
	    							</select>
	    						</div>
	    						<div class="form-group col-sm-3">
	    							<label>책 상태</label>
	    							<select name="bookCondition" class="form-control">
	    								<option value="깔끔" selected>깔끔</option>
	    								<option value="낙서">낙서</option>
	    								<option value="부분 파손">부분 파손</option>
	    								<option value="얼룩짐">얼룩짐</option>
	    							</select>
	    						</div>
	    					</div>
	    					<div class="modal-footer">
	    						<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
	    						<button type="submit" class="btn btn-primary">등록하기</button>
	    					</div>
	    				</form>
	    			</div>
	    		</div>
	    	</div>
	    </div>
		<script src="./js/jquery.min.js"></script>
		<script src="./js/popper.js"></script>
		<script src="./js/bootstrap.min.js"></script>
</body>
</html>