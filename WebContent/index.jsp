<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book App</title>

<link rel="stylesheet" href="./css/my.css">
<link rel="stylesheet" href="./css/main.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>
	<%@ include file="common/header.jsp"%>
	<div class="container">
	<section>
		<h3>link list</h3>
		<ul>
			<c:if test="${empty login}">
				<li><a href="${pageContext.request.contextPath}/login.jsp">Login</a><br /></li>
			</c:if>
			<c:if test="${!empty login}">
				<li><a href="${pageContext.request.contextPath}/logout.do">Logout [${login.id}]</a><br /></li>
				<li><a href="${pageContext.request.contextPath}/bookList.do">Book List</a><br /></li>
				<li><a href="${pageContext.request.contextPath}/bookAdd.jsp">Book Add</a><br /></li>
				<li><a href="${pageContext.request.contextPath}/">Map</a><br /></li>
			</c:if>
		</ul>
	</section>
	</div>
	
	<%@ include file="common/footer.jsp"%>
</body>
</html>