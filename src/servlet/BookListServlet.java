package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO_Mariadb;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVO;
import vo.UserVO;

@WebServlet("/bookList.do")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html:charset=utf-8");

		// 로그인했는지 판단
		HttpSession session = request.getSession();
		UserVO login = (UserVO) session.getAttribute("login");

		if (login == null) {
			request.setAttribute("msg", "BookList는 로그인이 필요합니다.");
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			return;

		}

		BookDAO_Mariadb dao = new BookDAO_Mariadb();
		BookService service = new BookServiceImpl(dao);
		List<BookVO> list = service.bookList();

		// 컨트롤러로 뻴 예정 : 서블릿과 jsp 유지보수가 2배가 되므로
		request.setAttribute("bookList", list); // jsp파일과 반드시 동일하게
		String page = "/bookList.jsp";

		// jsp에서도 사용가능 하도록 공유함
		getServletContext().getRequestDispatcher(page). // 흐름처리
				forward(request, response);

	}

}
