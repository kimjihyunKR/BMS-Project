<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home</title>
<link rel="stylesheet" href="./css/my.css">
<link rel="stylesheet" href="./css/main.css">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<%@ include file="common/header.jsp"%>
	<div class="container">

		<form action="${pageContext.request.contextPath}/bookSearch.do"
			method="POST">
			<div class="search-box">
				<button type="button" id="goAddPage">add</button>
				<select name="condition">
					<option value="title">title</option>
					<option value="publisher">publisher</option>
				</select> <input type="text" name="keyword">
				<button type="submit">search</button>
			</div>
		</form>
		<section>
			<form action="bookDelete.do" method="GET">
				<table>
					<thead>
						<tr>
							<th>id</th>
							<th>title</th>
							<th>publisher</th>
							<th>price</th>
							<th>
								<button type="submit" id="delete-btn" onclick="return f1()">삭제</button>
							</th>
						</tr>
					</thead>

					<c:forEach var="data" items="${bookList}">
						<tr>
							<td>${data.bookno}</td>
							<td><a href="viewBook.do?bookno=${data.bookno}">${data.title}</a>
							</td>
							<td>${data.publisher}</td>
							<td>${data.price}</td>
							<td><input type="checkbox" name="bookno"
								value="${data.bookno}"></td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</section>


	</div>
	<%@ include file="common/footer.jsp"%>

	<script>
	function f1() {
		return confirm("진심임?");
	}
	
	document.getElementById("goAddPage").addEventListener('click', (e)=>{
		location.href="bookAdd.jsp";
	 });
	
	
	</script>
</body>
</html>


