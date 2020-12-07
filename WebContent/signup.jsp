<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<link rel="stylesheet" href="./css/my.css">
<link rel="stylesheet" href="./css/main.css">
<link rel="stylesheet" href="./css/grid-system.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>
	<%@ include file="common/header.jsp"%>
	<div class="container">
		<section>
			<h2>Sign Up</h2>
			<p></p>
			<form action="signup.do" method="post">
				<dl>
					<dt>ID</dt>
					<dd>
						<input type="text" name="id" required="required">
					</dd>

					<dt>PW</dt>
					<dd>
						<input type="password" name="password" required="required">
					</dd>
					
					<dt>Name</dt>
					<dd>
						<input type="text" name="name" required="required">
					</dd>
					
				</dl>
				<div class="btn-box">
					<button type="reset">reset</button>
					<button type="submit" id="btn-singup">sign up</button>
				</div>
			</form>
		</section>
	</div>
	<%@ include file="common/footer.jsp"%>
</body>
</html>