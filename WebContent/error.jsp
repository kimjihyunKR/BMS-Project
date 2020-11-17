<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--에러 전용 페이지 --%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="common/header.jsp"%>
	<section>
		<h3>Error Page</h3>
		<div>사용 점검중입니다.</div>
		<div style="color:#ffoooo;">${execption}</div>
	</section>
	<%@ include file="common/footer.jsp"%>
</body>
</html>