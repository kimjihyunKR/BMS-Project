/**
 * 
 */

let allCheck = document.getElementById("allCheck");
let cartItems = document.getElementsByClassName("cartItem");
let num = 0;

allCheck.addEventListener('click', (e) => {
	if (allCheck.checked) {
		console.log(allCheck.checked);
		for (let i = 0; i < cartItems.length; i++) {
			cartItems[i].checked = true;
		}

	} else {
		console.log(allCheck.checked);
		for (let i = 0; i < cartItems.length; i++) {
			cartItems[i].checked = false;
		}
	}
}
);

document.getElementById("delete-btn").addEventListener('click', () => {
	checkedNum();
	if (num == 0) {
		confirm('장바구니에서 삭제할 책을 선택해주세요!');
	} else {
		if (confirm(num + '개를 정말 삭제하겠습니까?') == true) {
			chartf.submit();
		}
	}

})

function checkedNum() {
	for (let i = 0; i < cartItems.length; i++) {
		if (cartItems[i].checked) num++;
	}
}
