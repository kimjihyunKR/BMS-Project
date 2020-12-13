<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="./common/top.jsp"%>
<link type="text/css" rel="stylesheet" href="./public/css/lightslider.css" />

<title>BMS</title>
</head>
<body>

	<%@ include file="./common/header.jsp"%>

	<section id="mainBanner">
		<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">

			<ol class="carousel-indicators">
				<li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"></li>
				<li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"></li>
				<li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"></li>
			</ol>

			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="public/img/banner-1.jpg" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="public/img/banner-2.jpg" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="public/img/banner-3.jpg" class="d-block w-100" alt="...">
				</div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="visually-hidden">Previous</span>
			</a>
			<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="visually-hidden">Next</span>
			</a>
		</div>
	</section>

	<div class="container">



		<section>
			<p class="fs-7">찜해둔 책을 읽어보세요!</p>
			<a href="${pageContext.request.contextPath}/viewCart.do" class="reset-a">
				<h1 class="fs-4 fw-bold">장바구니🛒 ></h1>
			</a>
			<hr>
			<c:if test="${empty login}">
				<p class="text-primary fs-7">
					<a href="${pageContext.request.contextPath}/login.jsp" class="nav-link active"> 로그인을 하면 더욱 편하게 둘러보실 수 있어요 :) </a>
				</p>
			</c:if>
			<c:if test="${!empty login}">
				<ul id="responsive" class="cs-hidden ">
					<c:forEach var="data" items="${cartList}">
						<li>
							<div class="col h-100">
								<a href="viewBook.do?bookno=${data.bookno}" class="reset-a">
									<div class="card ">
										<div class="imagebox">
											<img src="./upload/${data.img}" class="card-img-top" alt="...">
										</div>

										<div class="card-body">
											<h5 class="card-title fs-6">${data.title}</h5>
											<p class="card-text fs-6">
												<span>${data.publisher}</span><br> <span>${data.price}</span>
											</p>
										</div>
									</div>
								</a>
							</div>
						</li>
					</c:forEach>

				</ul>
			</c:if>

		</section>



		<section>
			<p class="fs-7">BMS의 모든 책</p>
			<a href="#">
				<h1 class="fs-4 fw-bold">
					<a href="${pageContext.request.contextPath}/bookList.do" class="reset-a"> 모두 보기📚 > </a>
				<h1>
			</a>

			<hr>
			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 row-cols-xl-4 g-4">
				<c:forEach var="data" items="${bookList}">
					<div class="col">
						<a href="viewBook.do?bookno=${data.bookno}" class="reset-a">
							<div class="card h-100">
								<div class="imagebox">
									<img src="./upload/${data.img}" class="card-img-top" alt="...">
								</div>

								<div class="card-body">
									<h5 class="card-title fs-6">${data.title}</h5>
									<p class="card-text fs-6">
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



		<%@ include file="./common/footer.jsp"%>
	</div>

	<script type="text/javascript" src="./public/src/lightslider.js"></script>
	<script type="text/javascript">
		var timer = 0;
		var color = [ '#449e60', '#d76155', '#e97b4a', '#61a4cf' ];
		var banner = document.getElementById('mainBanner');
		var items = document.getElementsByClassName('carousel-item');

		$(document).ready(function() {
			$('#responsive').lightSlider({
				item : 5,
				loop : false,
				adaptiveHeight : true,
				pager : true,
				slideMove : 2,
				easing : 'cubic-bezier(0.25, 0, 0.25, 1)',
				speed : 600,
				responsive : [ {
					breakpoint : 800,
					settings : {
						item : 3,
						slideMove : 1,
						slideMargin : 6,
					}
				}, {
					breakpoint : 480,
					settings : {
						item : 2,
						slideMove : 1
					}
				} ]
			});
		});
	</script>


	<%@ include file="./common/bottom.jsp"%>