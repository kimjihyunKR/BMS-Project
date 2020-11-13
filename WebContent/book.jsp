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
		제목: ${title} <br/>
		출판사: ${publisher} <br/>
		가격 : ${price} <br/>
		<button type="button">장바구니</button>
		<button type="button">구매하기</button>
</body>
</html>