<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="./common/top.jsp"%>
<title>Book:add</title>
</head>
<body>
	<%@ include file="common/header.jsp"%>
	<div class="container">
		<section>
			<p class="text-primary">관리자 메뉴</p>
			<c:if test="${empty book}">
				<h2 class="fs-2 fw-bold">책 추가하기 📚</h2>
			</c:if>
			<c:if test="${!empty book}">
				<h2 class="fs-2 fw-bold">책 수정하기 ✍🏻</h2>
			</c:if>
			
			
			<hr class="margin-col-2">

			<form action="" name="f" method="post" enctype="multipart/form-data" class="margin-col-2">
				<input type="hidden" name="bookno" value="${book.bookno }" />
				<div class="mb-3">
					<label for="bookTitleLabel" class="form-label text-primary">제목</label> 
					<input type="text" class="form-control" id="bookTitleLabel" name="title" placeholder="책 이름을 입력하세요"  value="${book.title }">
				</div>

				<div class="mb-3">
					<label for="bookPubLabel" class="form-label text-primary">작가</label> 
					<input type="text" class="form-control" id="bookPubLabel" name="publisher" placeholder="작가 이름을 입력하세요" value="${book.publisher }">
				</div>
				
				<div class="mb-3">
					<label for="bookTitleLabel" class="form-label text-primary">가격</label> 
					<input type="number" class="form-control" id="bookTitleLabel" name="price" placeholder="책 가격을 입력하세요"  value="${book.price }">
				</div>
				
				<div class="mb-3">
					<label for="bookImgLabel" class="form-label text-primary">책 커버 이미지</label> <br>
					<c:if test="${!empty book}">
						<img src="./upload/${book.img}" id="imgCheck" class="card-img-top w-25 p-3 img-thumbnail rounded mb-3" alt="${book.title} cover image" >
						<input type="hidden" name="exImg" value="${book.img}" >
						<input type="file" name="newImg" id="bookImgLabel" class="form-control"  />
					</c:if>
					<c:if test="${empty book}">
						<img id="imgCheck" class="card-img-top w-25 p-3 img-thumbnail rounded mb-3"/>
						<input type="file" name="img" id="bookImgLabel" class="form-control" />
					</c:if>
					
				</div>
				
				<div class="mb-3">
  					<label for="bookInfoLabel" class="form-label text-primary">상세설명</label>
  					
  					<textarea name="detail" id="bookInfoLabel" rows="10" class="form-control" value="">${book.detail}</textarea>
				</div>

				<div class="row gap-3 col-4 margin-col-2" id="btn-box">
					<c:if test="${empty book}">
						<button type="button" id="addBtn" class="btn btn-outline-primary col">추가하기</button>
					</c:if>
					<c:if test="${!empty book}">
						<button type="button" id="modifyBtn" class="btn btn-outline-primary col">수정하기</button>
					</c:if>
					<button type="reset" class="btn btn-outline-primary col">초기화</button>
				</div>

			</form>
		</section>
		
	</div>

	<%@ include file="common/footer.jsp"%>

	<script>
	let btnBox = document.getElementById("btn-box");
	let imgCheck = document.getElementById("imgCheck");
	let selectImg = document.getElementById("bookImgLabel");
	
	btnBox.addEventListener('click', (e)=>{
		 let target = e.target;
		 console.log(target.id);
		 switch( target.id ){
			 case 'addBtn' : 
				 f.action = "addBook.do";
				 f.submit();
				 break;
			 case 'modifyBtn' : 
				 console.log('click');
				 f.action = "updateBook.do";
				 f.submit();
				 break;
		 }
	 });
	
	selectImg.addEventListener('change', (e)=>{
		console.log('change');
		readURL(selectImg);
		
	});
	
	function readURL(input) {
		 if (input.files && input.files[0]) {
		  var reader = new FileReader();
		  
		  reader.onload = function (e) {
			  imgCheck.setAttribute('src',e.target.result);
		  }
		  reader.readAsDataURL(input.files[0]);
		 }
	}
	
	
	</script>
<%@ include file="./common/bottom.jsp"%>