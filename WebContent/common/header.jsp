<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header>
	<nav>
		<ul>
			<li>
				<h1 id="logo">
					<a href="index.jsp">BMS</a>
				</h1>
			</li>
			<li><a href="chart1.jsp"> chart </a></li>
			<li><a href="#"> About </a></li>
			<li><a href="#"> Map </a></li>
			<c:if test="${empty login}">
				<li><a href="login.jsp"> LogIn </a></li>
				<br>
			</c:if>
			<c:if test="${!empty login }">
				<li><a href="logout.do">LogOut</a></li>
				<li><a href="#"> [${login.id}] </a></li>
				<br>
			</c:if>
		</ul>
	</nav>
</header>
