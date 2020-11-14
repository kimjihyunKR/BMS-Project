<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book : ${title}</title>
</head>
<body>
	<h2>${title}</h2>
	<form action="addBook.do" method="post">
		제목 :<input type="text" id="" name="title" value="${title}"><br /> 
		출판사 :<input type="text" id="" name="publisher" value="${title}"><br /> 
		가격 :<input type="number" id="" name="price" value="${title}"><br />
		<input type="submit" value="등록">
		<input type="reset">
	</form>
</body>
</html>