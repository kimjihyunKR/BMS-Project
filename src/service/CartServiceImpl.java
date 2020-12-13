package service;

import java.util.ArrayList;
import java.util.List;

import dao.CartDAO_Mariadb;
import vo.BookVO;
import vo.CartVO;

public class CartServiceImpl  implements CartService {
	
	private CartDAO_Mariadb dao = null;
	
	public CartServiceImpl() {}
	
	public CartServiceImpl( CartDAO_Mariadb dao ) {
		this.dao = dao;
	}

	@Override
	public List<CartVO> cartList() {
		return dao.cartList();
	}

	@Override
	public List<CartVO> cartListByUser(String id) {
		return dao.cartListByUser(id);
	}
	
	public List<BookVO> bookListInCart(BookService serviceB, List<CartVO> cartList){
		List<BookVO> bookList = new ArrayList<BookVO>();
		BookVO vo1 = null;
		for (CartVO item : cartList) {
			vo1 = serviceB.getBook(item.getBookno());
			if (vo1 == null) continue;
			bookList.add(vo1);
		}
		return bookList;
	}

	@Override
	public void cartAdd(CartVO vo) {
		dao.cartAdd(vo);
	}

	@Override
	public void cartDelete(int cart_id) {
		dao.cartDelete(cart_id);
		
	}

	@Override
	public void cartUpdate(CartVO vo) {
		// TODO Auto-generated method stub
		
	}
	
	public CartVO getCart(int cart_id){
		return dao.getCart(cart_id);
	}

	@Override
	public List<CartVO> getCartByBookno(int bookno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartVO getcartByUseNBooks(String user, int bookno) {
		return dao.getcartByUseNBooks(user, bookno);
	}



}
