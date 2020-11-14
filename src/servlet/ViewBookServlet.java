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

@WebServlet("/viewBook.do")
public class ViewBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html:charset=utf-8");

		BookDAO_Mariadb dao = new BookDAO_Mariadb();
		BookService service = new BookServiceImpl(dao);

		// request에서 입력한 값 받아오기;
		String req = request.getParameter("bookno");
		if (req != null) {
			int bookno = Integer.parseInt(req);

			// 책 정보 가져오기
			BookVO vo = service.getBook(bookno);
			// 책 정보 넘겨주기
			request.setAttribute("book", vo);
		}
		String page = "/bookView.jsp";
		getServletContext().getRequestDispatcher(page). // 흐름처리
				forward(request, response);
	}

}
