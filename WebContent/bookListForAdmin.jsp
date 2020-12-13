<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="./common/top.jsp"%>
<title>BMS:Book List</title>
</head>
<body>
	<%@ include file="common/header.jsp"%>

	<div class="container">
		<section>
			<p class="text-primary">관리자 메뉴</p>
			<h1 class="fs-4 fw-bold">전체 목록 관리 📑</h1>
			<hr class="margin-col-2">
			<button type="button" id="delete-btn" class="btn btn-primary float-end">삭제</button>
			<table class="table">
				<form action="deleteBook.do" method="post" name="chartf">
					<thead>
						<tr>
							<th scope="col">no</th>
							<th scope="col">책 제목</th>
							<th scope="col" class="text-center">작가</th>
							<th scope="col" class="text-center">가격</th>
							<th scope="col" class="text-center"><input type="checkbox" id="allCheck" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="book" items="${bookList}">
							<tr>
								<td>${book.bookno}</td>
								<td><a href="viewBook.do?bookno=${book.bookno}" class="reset-a"> ${book.title} </a></td>
								<td class="text-center">${book.publisher}</td>
								<td class="text-center">${book.price}</td>
								<td scope="row" class="text-center"><input type="checkbox" name="bookno" value="${book.bookno}" class="cartItem" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</form>
			</table>

		</section>
	</div>





	<%@ include file="common/footer.jsp"%>
	<script src="./public/src/chartView.js"></script>

	<%@ include file="./common/bottom.jsp"%>