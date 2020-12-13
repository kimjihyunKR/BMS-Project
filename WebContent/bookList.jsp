<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="./common/top.jsp"%>
<title>My Home</title>
</head>
<body>
	<%@ include file="common/header.jsp"%>
	<div class="container">

		<form action="${pageContext.request.contextPath}/searchBook.do" method="POST" class="col-8 mx-auto mt-5">
			<div class="row g-3">
				<div class="col-md-3">
					<select name="condition" class="form-select form-select ">
						<option value="title">title</option>
						<option value="publisher">publisher</option>
					</select> 
				</div>
				<div class="col-md-9">
					<div class="input-group">
						<input type="text" name="keyword" class=" form-control " placeholder="검색어를 입력하세요">
						<button type="submit" class="btn btn-outline-primary" >search</button>
					</div>
				</div>
			</div>
		</form>
		
		<hr class="m-5">
		
		<section>
			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-xl-4 g-4">
				<c:forEach var="data" items="${bookList}">
					<div class="col">
						<a href="viewBook.do?bookno=${data.bookno}" class="reset-a">
							<div class="card h-100">
								<img src="./upload/${data.img}" class="card-img-top" alt="${data.title} cover image" >
								<div class="card-body">
									<h5 class="card-title">${data.title}</h5>
									<p class="card-text">
										<span>writer</span><br> 
										<span>${data.publisher}</span><br> 
										<span>${data.price}</span>
									</p>
								</div>
							</div>
						</a>

					</div>
				</c:forEach>
			</div>
		</section>


	</div>
	<%@ include file="common/footer.jsp"%>

	<%@ include file="./common/bottom.jsp"%>