package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO_Mariadb;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVO;


@WebServlet("/addBook.do")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html:charset=utf-8");
		
		BookDAO_Mariadb dao = new BookDAO_Mariadb();
		BookService service = new BookServiceImpl(dao);
		
		// request에서 입력한 값 받아오기
		String title = request.getParameter("title");
		String publisher = request.getParameter("publisher");
		int price = Integer.parseInt(request.getParameter("price"));
		// vo에 담아주기
		BookVO vo = new BookVO();
		vo.setTitle(title);
		vo.setPublisher(publisher);
		vo.setPrice(price);
		// db에 등록하기
		service.bookAdd(vo);
		
		//다시 bookList화면으로 가기
//		response.sendRedirect("bookList.jsp");
		response.sendRedirect("bookList.do");
		
	}

}
