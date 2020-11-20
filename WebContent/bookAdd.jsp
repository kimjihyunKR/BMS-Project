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
		<h2>${title}</h2>
		<form action="addBook.do" method="post" enctype="multipart/form-data">
			제목 :<input type="text" id="" name="title" value=""><br />
			출판사 :<input type="text" id="" name="publisher" value=""><br />
			가격 :<input type="number" id="" name="price" value=""><br />
			<hr />
			<label>책 이미지 : <input type="file" name="img" /></label><br />
			<hr />
			<input type="submit" value="등록">
			<input type="reset">
		</form>
	</section>
	<%@ include file="common/footer.jsp"%>
</body>
</html>