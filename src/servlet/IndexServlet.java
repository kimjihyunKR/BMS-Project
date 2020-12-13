package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO_Mariadb;
import dao.CartDAO_Mariadb;
import dao.UserDAO_Mariadb;
import service.BookService;
import service.BookServiceImpl;
import service.CartService;
import service.CartServiceImpl;
import service.Passport;
import service.UserService;
import service.UserServiceImpl;
import vo.BookVO;
import vo.CartVO;
import vo.UserVO;

/**
 * Servlet implementation class homeServlet
 */

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html:charset=utf-8");
		
		BookDAO_Mariadb daoB = new BookDAO_Mariadb();
		BookService serviceB = new BookServiceImpl(daoB);
		
		UserDAO_Mariadb daoU = new UserDAO_Mariadb();
		UserService serviceU = new UserServiceImpl(daoU);
		
		CartDAO_Mariadb daoC = new CartDAO_Mariadb();
		CartService serviceC = new CartServiceImpl(daoC);
		
		Passport passport = new Passport(request);
		
		List<BookVO> list = serviceB.bookList();
		
		if(passport.isLogined()) {	//만약 로그인을 했다면
		
			UserVO vo = passport.getUser();
			
			List<CartVO> cartList =serviceC.cartListByUser(vo.getId());
			System.out.println(vo.getId() + " 의 장바구니 : " + cartList);

			List<BookVO> booklistInCart = serviceC.bookListInCart(serviceB, cartList);
			request.setAttribute("cartList", booklistInCart);
		}

		// 컨트롤러로 뻴 예정 : 서블릿과 jsp 유지보수가 2배가 되므로
		request.setAttribute("bookList", list);
		String page = "/index.jsp";

		// jsp에서도 사용가능 하도록 공유함
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
	     dispatcher.forward(request,response);
		return;
		
	}
}
