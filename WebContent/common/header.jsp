<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header>
	<nav class="navbar navbar-expand-lg navbar-light reset-a ">
		<ul class="container navbar-nav  mb-2 mb-lg-0 ">
			<div class="navbar gap-2 d-flex float-start" id="navbarNavAltMarkup">
				<li class="nav-item "><a href="${pageContext.request.contextPath}/home" class="navbar-brand nav-link active">BMS</a></li>
				<li class="nav-item"><a href="${pageContext.request.contextPath}/home" class="nav-link active"> About </a></li>
				<li class="nav-item"><a href="${pageContext.request.contextPath}/bookList.do" class="nav-link active">See All</a></li>
			</div>

			<div class="d-flex">
				<c:if test="${empty sessionScope.login}">
					<li class="nav-item"><a href="${pageContext.request.contextPath}/login.jsp" class="nav-link active">🔑</a></li>
				</c:if>
				<c:if test="${!empty sessionScope.login}">
					<li class="nav-item dropdown"><a class="nav-link dropdown-toggle active" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">${login.id} 📚</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/viewCart.do">🛒 cart</a></li>
							<li><a class="dropdown-item" href="logout.do">🚪 Log out</a></li>
							<c:if test="${ sessionScope.login.role eq 'admin' }">
								<li ><a href="${pageContext.request.contextPath}/bookListForAdmin.do" class="dropdown-item">📑 Book List</a></li>
								<li ><a href="${pageContext.request.contextPath}/bookEdit.jsp" class="dropdown-item">✍🏻 Book Add</a></li>
							</c:if>
						</ul></li>
				</c:if>
			</div>

		</ul>
	</nav>
</header>
