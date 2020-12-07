<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book:add</title>
<link rel="stylesheet" href="./css/my.css">
<link rel="stylesheet" href="./css/main.css">
<link rel="stylesheet" href="./css/grid-system.css">
</head>
<body>
	<%@ include file="common/header.jsp"%>
	<div class="container">
		<section>
			<h2>Add book</h2>

			<form action="" name="f" method="post" enctype="multipart/form-data">
				<dl>
					<dt>title</dt>
					<dd>
						<input type="text" id="" name="title" value="${book.title}">
					</dd>
					<dt>publisher</dt>
					<dd>
						<input type="text" id="" name="publisher"
							value="${book.publisher}">
					</dd>

					<dt>price</dt>
					<dd>
						<input type="number" id="" name="price" value="${book.price}">
					</dd>
					<dt>book thumnail</dt>
					<dd>
						<input type="file" name="img" />
					</dd>
				</dl>
				<div class="btn-box">
					<button type="button" id="addBtn">add</button>
					<button type="reset">reset</button>
				</div>

			</form>
		</section>
	</div>

	<%@ include file="common/footer.jsp"%>

	<script>
	 document.getElementById("addBtn").addEventListener('click', (e)=>{
		 f.action = "addBook.do";
		 f.submit();
	 });
	</script>
</body>
</html>