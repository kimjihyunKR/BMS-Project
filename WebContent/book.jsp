<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book 등록</title>
</head>
<body>
	<h2>Book 등록</h2>
	<form action="addBook.do" method="POST">
		제목: <input type="text" id="" name="title"> <br/>
		출판사: <input type="text" id="" name="publisher"> <br/>
		가격 : <input type="number" id="" name="price"> <br/>
		
		<button type="submit">등록</button>
		<button type="reset">reset</button>
	</form>
</body>
</html>