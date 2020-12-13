<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="./common/top.jsp"%>
<title>Book:${book.title}</title>
</head>
<body>
	<%@ include file="common/header.jsp"%>
	<div class="container">

		<section class="row gap-3 d-flex align-items-center">
			<div id="img-box" class="col-5 com-xs-12 img-fluid">
				<c:if test="${!empty book.img}">
					<img id="book-img" src="./upload/${book.img}" class="img-fluid" />
				</c:if>
			</div>

			<div class="book-info col-6">
				<form action="" name="f" method="POST">
					<input type="hidden" name="bookno" id="${book.bookno}" value="${book.bookno}">
					<h1 class="fs-4 fw-bold">${book.title}</h1>
					<div class="margin-col-2">
						<p class="fs-6">&#8212; ${book.publisher}</p>
						<p class="fs-6 text-danger">${msg}</p>
						
					</div>
					<h3 class="fs-4 fw-bold align-right text-end">${book.price} &#8361;</h3>
				</form>

				<div class="row gap-2 margin-col-1">
					<button type="button" id="btn-chart" class="btn btn-outline-primary col">cart</button>
					<button type="button" id="btn-buy" class="btn btn-primary col">buy</button>
				</div>

				<c:if test="${sessionScope.login.role == 'admin'}">
					<div class=" margin-col-1 ">
						<hr>
						<p class="fs-7">관리자 메뉴</p>
						<button type="button" id="btn-bookModify" class="btn btn-outline-primary">편집하기</button>
						<button type="button" id="btn-bookDelete" class="btn btn-outline-primary">삭제하기</button>
					</div>
				</c:if>

			</div>
		</section>
		<section class="book-desc col-12 ">
			<h1 class="sr-only" >${book.title}의 상세정보</h1>
			<p>
			${book.getFormattedDetail()}
			</p>
		</section>

		<hr>
		<%@ include file="common/faqSection.jsp"%>

	</div>
	<!-- container -->
	
	<%@ include file="common/footer.jsp" %>

	<script>

	 // List
	 document.getElementById("btn-chart").addEventListener('click', (e)=>{
		 f.action = "addCart.do";
		 f.submit();
	 });
	 
	 document.getElementById("btn-buy").addEventListener('click', (e)=>{
		 f.action = "addCart.do";
		 f.submit();
	 });
	 
	//Edit
	 document.getElementById("btn-bookModify").addEventListener('click', (e)=>{
		 f.action = "modifyBook.do";
		 f.submit();
	 });
	
	 //Delete
	 document.getElementById("btn-bookDelete").addEventListener('click', (e)=>{
		 if(confirm("Are you sure, DELETE?")) {
			 f.action = "deleteBook.do";
			 f.submit();
		 }
	 });
	 
	</script>
	<%@ include file="./common/bottom.jsp"%>