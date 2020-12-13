package service;

import java.util.List;

import vo.BookVO;
import vo.CartVO;

public interface CartService{
	
	List<CartVO> cartList();
	
	List<CartVO> cartListByUser(String id);
	
	List<CartVO> getCartByBookno(int bookno);
	
	void cartAdd(CartVO vo);
	
	void cartDelete(int cart_id);
	
	void cartUpdate(CartVO vo);
	
	CartVO getCart(int cart_id);
	
	CartVO getcartByUseNBooks(String user, int bookno);

	List<BookVO> bookListInCart(BookService serviceB, List<CartVO> cl);
	
}
