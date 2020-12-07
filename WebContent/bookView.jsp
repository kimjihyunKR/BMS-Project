<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book:${book.title} Edit</title>
<link rel="stylesheet" href="./css/my.css">
<link rel="stylesheet" href="./css/main.css">
<link rel="stylesheet" href="./css/grid-system.css">
<link rel="stylesheet" href="./css/bookViewStyle.css">
</head>
<body>
	<%@ include file="common/header.jsp"%>
	<div class="container">
		<section class="row">
			<div id="img-box" class="col-5">
				<c:if test="${empty book.img}">
					<span>none img</span>
				</c:if>
				<c:if test="${!empty book.img}">
					<img id="book-img" src="./upload/${book.img}" />
				</c:if>
			</div>
			<div class="book-info col-7">
				<form action="" name="f" method="POST">
					<input type="hidden" name="bookno" id="${book.bookno}"
						value="${book.bookno}">
					<h1>${book.title}</h1>
					<span>${book.publisher}</span>
					<h3 class="book-price">${book.price}</h3>
				</form>

				<div class="btn-box">
					<c:if test="${login.role eq 'admin'}">
						<button type="button" id="btn-bookModify">modify</button>
						<button type="button" id="btn-bookDelete">delete</button>
					</c:if>
					<button type="button" id="btn-bookList">list</button>
				</div>
			</div>
		</section>
		<section class="book-desc col-12">
			<p>A book is a medium for recording information in the form of
				writing or images, typically composed of many pages (made of
				papyrus, parchment, vellum, or paper) bound together and protected
				by a cover.[1] The technical term for this physical arrangement is
				codex (plural, codices). In the history of hand-held physical
				supports for extended written compositions or records, the codex
				replaces its predecessor, the scroll. A single sheet in a codex is a
				leaf and each side of a leaf is a page.</p>
		</section>

	</div>
	<!-- container -->
	<%@ include file="common/footer.jsp"%>

	<script>
	var imgBox = document.getElementById("img-box");
	 var img = document.getElementById("book-img");
	 console.log(imgBox.clientWidth);
	 imgBox.style.height = imgBox.clientWidth-15 + "px";
	 console.log(img.getAttribute('src'));
	 imgBox.style.backgroundImage = "url('" + img.getAttribute('src') +"')";
	 img.parentNode. removeChild(img);
	//Edit
	 document.getElementById("btn-bookModify").addEventListener('click', (e)=>{
		 f.action = "bookModify.do";
		 f.submit();
	 });
	
	 //Delete
	 document.getElementById("btn-bookDelete").addEventListener('click', (e)=>{
		 if(confirm("Are you sure, DELETE?")) {
			 f.action = "bookDelete.do";
			 f.submit();
		 }
	 });
	 
	 // List
	 document.getElementById("btn-bookList").addEventListener('click', (e)=>{
		 f.action = "bookList.do";
		 f.submit();
	 });
	 
	 
	</script>
</body>
</html>