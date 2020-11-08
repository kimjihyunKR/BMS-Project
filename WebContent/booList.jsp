<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="vo.BookVO"%>
<%@page import="java.util.List"%>
<%@ page import="dao.BookDAO_Mariadb"%>
<%@ page import="service.BookService"%>
<%@ page import="service.BookServiceImpl"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home</title>
<link rel="stylesheet" href="./css/my.css">
</head>
<body>
	<h1>Book List</h1>
	<table class="tableb">
		<thead>
			<tr>
				<th>id</th>
				<th>title</th>
				<th>publisher</th>
				<th>price</th>
			</tr>
		</thead>
		<%
			BookDAO_Mariadb dao = new BookDAO_Mariadb();
			BookService service = new BookServiceImpl(dao);
			List<BookVO> list = service.bookList();
		for (BookVO data : list) {
		%>
		<tr>
			<td><%=data.getBookno()%></td>
			<td><%=data.getTitle()%></td>
			<td><%=data.getPublisher()%></td>
			<td><%=data.getPrice()%></td>
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>


