<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book:${book.title} Edit</title>
<link rel="stylesheet" href="./css/my.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<%@ include file="common/header.jsp"%>
	<h2>${book.title}</h2>
	<form action="" name="f" method="POST">
		<input type="hidden" name="bookno" id="${book.bookno}" value="${book.bookno}">
		<table>
			<tr>
				<td>제목</td>
				<td colspan="2">
					<span>${book.title}</span>
					<!-- input type="text" -->
				</td>
			</tr>

			<tr>
				<td>출판사</td>
				<td colspan="2">
					<span>${book.publisher}</span>
				</td>
			</tr>

			<tr>
				<td>가격</td>
				<td colspan="2">
					<span>${book.price}</span>
				</td>
			</tr>

			<tr>
				<td colspan="3">
					<c:if test="${empty book.img}">
						<img src="./img/empty.jpg" width="200" height="200" />
					</c:if>
					<c:if test="${!empty book.img}">
						<img src="./upload/${book.img}" width="200" height="200" />
					</c:if>
				</td>
			</tr>

			<tr>
				<td>
					<button type="button" id="bookEdit">수정</button>
				</td>
				<td>
					<button type="button" id="bookDelete">삭제</button>
				</td>
				<td>
					<button type="button" id="bookList">목록</button>
				</td>
			</tr>
		</table>
	</form>


	<script>
	//Edit
	 document.getElementById("bookEdit").addEventListener('click', (e)=>{
		 //f.action = "bookDelete.do";
		 //f.submit();
	 });
	
	 //Delete
	 document.getElementById("bookDelete").addEventListener('click', (e)=>{
		 if(confirm("정말 삭제?")) {
			 f.action = "bookDelete.do";
			 f.submit();
		 }
	 });
	 
	 // List
	 document.getElementById("bookList").addEventListener('click', (e)=>{
		 f.action = "bookList.do";
		 f.submit();
	 });
	</script>

	<%@ include file="common/footer.jsp"%>
</body>
</html>