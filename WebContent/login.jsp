<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<link rel="stylesheet" href="./css/my.css">
<link rel="stylesheet" href="./css/main.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>
	<%@ include file="common/header.jsp"%>
	<section>
		<h2>Login</h2>
		<c:if test="${empty msg }">
			<h3>로그인해주세요</h3>
		</c:if>
		<c:if test="${!empty msg }">
			<h3 style="color:#ff0000">${msg}</h3>
		</c:if>

		<form action="login.do" method="post">
			<table>
				<tr>
					<td>ID</td>
					<td>
						<input type="text" name="id" required="required" >
					</td>
				</tr>

				<tr>
					<td>PW</td>
					<td>
						<input type="password" name="password" required="required" >
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<button type="reset">reset</button>
						<button type="submit">Login</button>
						<button type="button">sign up</button>
					</td>
				</tr>
			</table>
		</form>
	</section>
	<%@ include file="common/footer.jsp"%>
</body>
</html>