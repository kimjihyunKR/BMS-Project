package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.BookDAO_Mariadb;
import dao.CartDAO_Mariadb;
import service.BookServiceImpl;
import service.CartService;
import service.CartServiceImpl;
import vo.CartVO;
import vo.BookVO;

public class CartTest1 {
	CartService service;
	BookServiceImpl serviceB;

	@BeforeEach
	void setUp() throws Exception {
		CartDAO_Mariadb dao = new CartDAO_Mariadb();
		service = new CartServiceImpl(dao);

		BookDAO_Mariadb daoB = new BookDAO_Mariadb();
		serviceB = new BookServiceImpl(daoB);
	}

//	@Test
	void list() {
		System.out.printf("|%10s |%10s |%10s |%10s\n", "cart_id", "user id", "bookno", "bookno.title");
		System.out.println("-------------------------------------------------");
		service.cartList().forEach(i -> {
			BookVO book = serviceB.getBook(i.getBookno());
			System.out.printf("|%10d |%10s |%10d |%10s\n", i.getCart_id(), i.getId(), i.getBookno(), book.getTitle());
		});
	}

	@Test
	void add() {
		CartVO vo = new CartVO();
		vo.setId("admin");
		vo.setBookno(21);

		service.cartAdd(vo);
		list();
	}
	
//	@Test
	void getCart() {
		System.out.println( service.getCart(0) );
	}

	@Test
	void delete() {
		CartVO vo = service.getCart(1);
		if(vo !=null) {
			System.out.println("before : " + vo);
			service.cartDelete(vo.getCart_id());
			System.out.println("gone..");
		}
		list();
	}



//	@Test
	void update() {
		CartVO vo = new CartVO();
		vo.setId("admin");
		vo.setBookno(30);

		service.cartAdd(vo);
	}
}
