<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home</title>

<link rel="stylesheet" href="./css/my.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	function f1() {
		return confirm("진심임?");
	}
</script>
</head>
<body>
	<%@ include file="common/header.jsp"%>
	<h1>Book List</h1>
	<hr>
	<form action="${pageContext.request.contextPath}/bookSearch.do" method="POST">
		<table>
			<tr>
				<td>
					<a href="bookAdd.jsp">등록</a>
				</td>
				<td>
					<select name="condition">
						<option value="title">도서 제목</option>
						<option value="publisher">작가명</option>
					</select>
				</td>
				<td>
					<input type="text" name="keyword">
				</td>
				<td>
					<button type="submit">검색</button>
				</td>
			</tr>
		</table>
	</form>
	<hr>

	<form action="bookDelete.do" method="GET">
		<table class="tableb">
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
					<td>
						<a href="viewBook.do?bookno=${data.bookno}">${data.title}</a>
					</td>
					<td>${data.publisher}</td>
					<td>${data.price}</td>
					<td>
						<input type="checkbox" name="bookno" value="${data.bookno}">
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>

	<%@ include file="common/footer.jsp"%>
</body>
</html>


