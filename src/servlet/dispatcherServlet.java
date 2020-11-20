package servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.BookDAO_Mariadb;
import dao.UserDAO_Mariadb;
import service.BookService;
import service.BookServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import vo.BookVO;
import vo.UserVO;

@WebServlet("*.do")
@MultipartConfig(maxFileSize = 1024*1024*5)
public class dispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html:charset=utf-8");

		String url = request.getRequestURI();
		String action = url.substring(url.lastIndexOf("/"));
		// log파일에 기록하기

		BookDAO_Mariadb daoB = new BookDAO_Mariadb();
		BookService serviceB = new BookServiceImpl(daoB);
		UserDAO_Mariadb daoU = new UserDAO_Mariadb();
		UserService serviceU = new UserServiceImpl(daoU);

		if (action.equals("/login.do")) {

			String id = request.getParameter("id");
			String pw = request.getParameter("password");
			UserVO login = serviceU.login(id, pw);

			if (login != null) {
				HttpSession session = request.getSession();
				session.setAttribute("login", login);

				String page = "/index.jsp";
				getServletContext().getRequestDispatcher(page). // 흐름처리
						forward(request, response);

			} else {
				// 로그인 실패
				// forwarding 서버단의 기억공간을 쉐어하기 위해서
				request.setAttribute("msg", "로그인 실패! 로그인 정보를 다시 입력하세요");
				getServletContext().getRequestDispatcher("/login.jsp"). // 흐름처리
						forward(request, response);
			}
			return;
		}

		if (action.equals("/logout.do")) {
			HttpSession session = request.getSession();

			if (session != null) {
				session.invalidate();
			}
			response.sendRedirect("/index.jsp");
			return;
		}

		if (action.equals("/bookList.do")) {
			// 로그인했는지 판단
			HttpSession session = request.getSession();
			UserVO login = (UserVO) session.getAttribute("login");

			if (login == null) {
				request.setAttribute("msg", "BookList는 로그인이 필요합니다.");
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				return;

			}

			List<BookVO> list = serviceB.bookList();

			// 컨트롤러로 뻴 예정 : 서블릿과 jsp 유지보수가 2배가 되므로
			request.setAttribute("bookList", list);
			String page = "/bookList.jsp";

			// jsp에서도 사용가능 하도록 공유함
			getServletContext().getRequestDispatcher(page). // 흐름처리
					forward(request, response);
			return;
		}

		if (action.equals("/addBook.do")) {

			// request에서 입력한 값 받아오기
			String title = request.getParameter("title");
			String publisher = request.getParameter("publisher");
			int price = Integer.parseInt(request.getParameter("price"));
			String fileName = null;
			// upload file path
			String path = request.getSession().getServletContext().getRealPath("/upload/");
			// 업로드한 파일 받아오기
			Collection<Part> p = request.getParts();

			for (Part data : p) {
				if (data.getContentType() != null) {
					fileName = data.getSubmittedFileName();
					System.out.println(fileName);
					if (fileName != null && fileName.length() != 0) {
						data.write(path + fileName);
					}
				}
			}
			
			// vo에 담아주기
			BookVO vo = new BookVO();
			vo.setTitle(title);
			vo.setPublisher(publisher);
			vo.setPrice(price);
			vo.setImg(fileName);
			// db에 등록하기
			serviceB.bookAdd(vo);

			// 다시 bookList화면으로 가기
			response.sendRedirect("bookList.do");
			return;
		}

		if (action.equals("/bookDelete.do")) {

			String[] bookno = request.getParameterValues("bookno");

			if (bookno != null) {
				for (int i = 0; i < bookno.length; i++) {
					serviceB.bookDelete(Integer.parseInt(bookno[i]));
				}
			}

			response.sendRedirect("bookList.do");
			return;
		}

		if (action.equals("/bookSearch.do")) {

			String condition = request.getParameter("condition");
			String keyword = request.getParameter("keyword");

			List<BookVO> list = serviceB.searchBook(condition, keyword);

			// 컨트롤러로 뻴 예정 : 서블릿과 jsp 유지보수가 2배가 되므로
			request.setAttribute("bookList", list); // jsp파일과 반드시 동일하게
			String page = "/bookList.jsp";

			// jsp에서도 사용가능 하도록 공유함
			getServletContext().getRequestDispatcher(page). // 흐름처리
					forward(request, response);

			return;
		}

		if (action.equals("/viewBook.do")) {

			// request에서 입력한 값 받아오기;
			String req = request.getParameter("bookno");
			if (req != null) {
				int bookno = Integer.parseInt(req);
				// 책 정보 가져오기
				BookVO vo = serviceB.getBook(bookno);
				// 책 정보 넘겨주기
				request.setAttribute("book", vo);
			}
			String page = "/bookView.jsp";
			getServletContext().getRequestDispatcher(page). // 흐름처리
					forward(request, response);
			return;
		}

	}

}
