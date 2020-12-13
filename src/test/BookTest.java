package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.BookDAO_Mariadb;
import service.BookService;
import service.BookServiceImpl;

public class BookTest {
	BookService service;
	
	@BeforeEach
	void setUp() throws Exception {
		BookDAO_Mariadb dao = new BookDAO_Mariadb();
		service = new BookServiceImpl(dao);
	}
	
	@Test
	void list() {
		System.out.printf("%5s |%30s |%10s |%10s |%30s\n", "id", "제목", "저자", "가격" , "이미지" );
		System.out.println("-------------------------------------------------");
		service.bookList().forEach(   i -> { 
			System.out.printf("%5d |%30s |%10s |%10d |%30s\n", i.getBookno(), i.getTitle(), i.getPublisher(), i.getPrice(), i.getImg());
		});
	}
	
//	@Test
//	void add() {
//		BookVO vo = new BookVO();
//		vo.setTitle("nodejs");
//		vo.setPublisher("mark");
//		vo.setPrice(2000);
//		
//		service.bookAdd(vo);
//	}
	
//	@Test
//	void getBook() {
//		System.out.println( service.getBook(10) );
//	}
//	
//	@Test
//	void delete() {
//		BookVO vo = service.getBook(12);
//		if(vo!=null) {
//			System.out.println("before : "+vo);
//			service.bookDelete(vo.getBookno());
//			System.out.println("gone...");
//		}
//	}
	
//	@Test
//	void update() {
//		BookVO vo = service.getBook(2);
//		if(vo != null) {
//			System.out.println(vo);
//			vo.setPrice(3300);
//			service.bookUpdate(vo);
//			System.out.println(service.getBook(vo.getBookno()));
//		}	
//	}
	
//	@Test
//	void search() {
//		List<BookVO> list = service.searchBook("publisher", "d");
////		list.forEach( i -> { System.out.println(i); });
//		System.out.println("------search------");
//		for(BookVO data : list) {
//			System.out.printf("id:%d \n제목:%s \n저자:%s \n가격:%d \n", data.getBookno(), data.getTitle(), data.getPublisher(), data.getPrice());
//			System.out.println("-----");
//		}
//	}

}
