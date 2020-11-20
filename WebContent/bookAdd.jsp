<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book:add</title>
<link rel="stylesheet" href="./css/my.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<%@ include file="common/header.jsp"%>
	<section>
		<c:if test="${!empty book.title }">
			<h2>${book.title}:수정</h2>
		</c:if>
		<c:if test="${empty book.title}">
			<h2>Add book</h2>
		</c:if>

		<form action="" name="f" method="post" enctype="multipart/form-data">
			제목 :
			<input type="text" id="" name="title" value="${book.title}">
			<br />
			출판사 :
			<input type="text" id="" name="publisher" value="${book.publisher}">
			<br />
			가격 :
			<input type="number" id="" name="price" value="${book.price}">
			<br />
			<hr />
			<c:if test="${empty book.img }">
				<label>책 이미지 : <input type="file" name="img" /></label>
				<br />
			</c:if>
			<c:if test="${!empty book.img }">
				책 이미지 :<br>
				<img src="./upload/${book.img}" width="200" height="200" />
				<label>이미지 변경 : <input type="file" name="img" /></label>
			</c:if>
			<hr />

			<c:if test="${!empty book.title }">
				<input type="hidden" name="bookno" value="${book.bookno}" />
				<button type="button" id="modifyBtn">수정하기</button>
			</c:if>

			<c:if test="${empty book.title}">
				<button type="button" id="addBtn">등록</button>
			</c:if>

			<!-- <input type="submit" value="등록"> -->
			<input type="reset">
		</form>
	</section>
	<%@ include file="common/footer.jsp"%>

	<script>
	 document.getElementById("modifyBtn").addEventListener('click', (e)=>{
		 f.action = "bookUpdate.do";
		 f.submit();
	 });
	 document.getElementById("addBtn").addEventListener('click', (e)=>{
		 f.action = "addBook.do";
		 f.submit();
	 });
	</script>
</body>
</html>