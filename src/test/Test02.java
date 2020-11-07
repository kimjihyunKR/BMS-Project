package test;

import dao.BookDAO_Mariadb;
import vo.BookVO;

public class Test02 {

	public static void main(String[] args) {
		BookDAO_Mariadb b =new BookDAO_Mariadb();
		
		BookVO vo = new BookVO();
		vo.setTitle("react");
		vo.setPublisher("lee");
		vo.setPrice(7700);

		//b.bookAdd(vo);
		
		System.out.println("---------all----------");
		b.bookList().forEach( i -> { System.out.println(i); });	
		
		System.out.println("----------search---------");
		b.bookSearch("publisher", "david").forEach( i -> { System.out.println(i); });	

	}

}
