<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./common/top.jsp"%>
<title>Login</title>

<link rel="stylesheet" href="./css/grid-system.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>
	<%@ include file="common/header.jsp"%>
	<div class="container clearfix">
		<section>
			<div>
				<!-- 책읽는 일러스트 -->
				<img alt="" src="">
			</div>
			<div class="col-6 float-end ">
				<h2 class="fs-3 fw-bold">🎉 Sign up</h2>
				<c:if test="${empty msg}">
					<h4 class="fs-6 margin-col-2">Welcom to BMS!</h4>
				</c:if>

				<form action="signup.do" method="post" class="margin-col-2">
					<div class="margin-col-2">
						<div class="form-floating mb-3">
							<input type="text" name="id" required="required" class="form-control" id="floatingInput" placeholder="name@example.com"><label for="floatingInput">ID</label>
						</div>
						<div class="form-floating mb-3">
							<input type="password" name="password" required="required" class="form-control" id="floatingPassword" placeholder="Password"> <label for="floatingPassword">Password</label>
						</div>
						<div class="form-floating">
							<input type="text" name="name" required="required" class="form-control" id="floatingInput2" placeholder="Password"> <label for="floatingInput2">Name</label>
						</div>

					</div>

					<div class="btn-box-col">
						<button type="submit" class="btn btn-primary col-12">sign up</button>
						<button type="reset" id="btn-singup" class="btn btn-outline-primary col-12">reset</button>
					</div>
				</form>
			</div>

		</section>

	</div>
	<%@ include file="common/footer.jsp"%>
	<%@ include file="./common/bottom.jsp"%>