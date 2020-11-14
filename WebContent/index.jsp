<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book App</title>

<link rel="stylesheet" href="./css/my.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>
	<%@ include file="common/header.jsp"%>
	<section>
		<h3>link list</h3>
		<ul>
			<li><a href="${pageContext.request.contextPath}/login.jsp">Login</a><br /></li>
			<li><a href="${pageContext.request.contextPath}/logout.do">Logout</a><br /></li>
			<li><a href="${pageContext.request.contextPath}/bookList.do">Book List</a><br /></li>
			<li><a href="${pageContext.request.contextPath}/bookAdd.jsp">Book Add</a><br /></li>
			<li><a href="${pageContext.request.contextPath}/">menu1</a><br /></li>
			<li><a href="${pageContext.request.contextPath}/">menu2</a><br /></li>
		</ul>
	</section>
	<%@ include file="common/footer.jsp"%>
</body>
</html>