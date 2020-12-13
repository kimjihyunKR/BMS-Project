package servlet;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("*.do")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
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
		CartDAO_Mariadb daoC = new CartDAO_Mariadb();
		CartService serviceC = new CartServiceImpl(daoC);

		Passport passport = new Passport(request);

		if (action.equals("/login.do")) {

			String id = request.getParameter("id");
			String pw = request.getParameter("password");
			UserVO login = serviceU.login(id, pw);

			if (login != null) {
				HttpSession session = request.getSession();
				session.setAttribute("login", login);

				String page = "/home";
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
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}

		// 회원 가입
		if (action.equals("/signup.do")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("password");
			String name = request.getParameter("name");

			UserVO newbie = new UserVO();
			newbie.setId(id);
			newbie.setPassword(pw);
			newbie.setName(name);
			serviceU.userAdd(newbie);
			// login 화면으로 넘어가기
			response.sendRedirect("login.jsp");
			return;
		}

		// 책 리스트 보여주기
		if (action.equals("/bookList.do")) {

			List<BookVO> list = serviceB.bookList();

			// 컨트롤러로 뻴 예정 : 서블릿과 jsp 유지보수가 2배가 되므로
			request.setAttribute("bookList", list);
			String page = "/bookList.jsp";

			// jsp에서도 사용가능 하도록 공유함
			getServletContext().getRequestDispatcher(page). // 흐름처리
					forward(request, response);
			return;
		}
		// 책 리스트 보여주기
		if (action.equals("/bookListForAdmin.do")) {

			List<BookVO> list = serviceB.bookList();

			// 컨트롤러로 뻴 예정 : 서블릿과 jsp 유지보수가 2배가 되므로
			request.setAttribute("bookList", list);
			String page = "/bookListForAdmin.jsp";

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
			String detail = request.getParameter("detail");
			String fileName = null;
			// upload file path
			String path = request.getSession().getServletContext().getRealPath("/upload/");
			// 업로드한 파일 받아오기
			Collection<Part> p = request.getParts(); // file의 데이터 타입

			for (Part data : p) {
				if (data.getContentType() != null) {
					fileName = data.getSubmittedFileName();
					System.out.println(fileName);
					if (fileName != null && fileName.length() != 0) {
						data.write(path + fileName);
					}
				} else {
					fileName = "empty.png"; // 기본값
				}
			}

			// vo에 담아주기
			BookVO vo = new BookVO();
			vo.setTitle(title);
			vo.setPublisher(publisher);
			vo.setPrice(price);
			vo.setImg(fileName);
			vo.setDetail(detail);
			// db에 등록하기
			serviceB.bookAdd(vo);

			// 다시 bookList화면으로 가기
			response.sendRedirect("bookListForAdmin.do");
			return;
		}

		if (action.equals("/deleteBook.do")) {

			String[] bookno = request.getParameterValues("bookno");

			if (bookno != null) {
				for (int i = 0; i < bookno.length; i++) {
					serviceB.bookDelete(Integer.parseInt(bookno[i]));
				}
			}

			response.sendRedirect("bookList.do");
			return;
		}

		if (action.equals("/modifyBook.do")) {
			String req = request.getParameter("bookno");
			System.out.println("modify : " + req);
			if (req != null) {
				int bookno = Integer.parseInt(req);// 책 정보 가져오기
				BookVO vo = serviceB.getBook(bookno);
				// 책 정보 넘겨주기
				request.setAttribute("book", vo);
			}

			String page = "/bookEdit.jsp";
			getServletContext().getRequestDispatcher(page). // 흐름처리
					forward(request, response);
			return;
		}

		if (action.equals("/updateBook.do")) {

			// input창에서 수정한 값 가져오기
			int bookno = Integer.parseInt(request.getParameter("bookno"));
			String title = request.getParameter("title");
			String publisher = request.getParameter("publisher");
			int price = Integer.parseInt(request.getParameter("price"));
			String detail = request.getParameter("detail");

			System.out.println(detail);
			String exImg = request.getParameter("exImg");
			String newImg = null;

			String fileName = null;

			String path = request.getSession().getServletContext().getRealPath("/upload/"); // upload file path
			Collection<Part> p = request.getParts(); // file의 데이터 타입
			for (Part data : p) {
				if (data.getContentType() != null) {
					fileName = data.getSubmittedFileName(); // 업로드한 파일 받아오기
					System.out.println("fileName : " + fileName);
					newImg = fileName;
					if (fileName != null && fileName.length() != 0) {
						data.write(path + fileName);
					}
				}
			}

			if (newImg.equals("") || newImg == null) {
				newImg = exImg;
			}

			BookVO vo = serviceB.getBook(bookno);
			// vo수정하기
			vo.setTitle(title);
			vo.setPublisher(publisher);
			vo.setPrice(price);
			vo.setImg(newImg);
			vo.setDetail(detail);

			serviceB.bookUpdate(vo);

			request.setAttribute("book", vo);
			String page = "/bookView.jsp";
			getServletContext().getRequestDispatcher(page). // 흐름처리
					forward(request, response);
			return;
		}

		if (action.equals("/searchBook.do")) {

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

		if (action.equals("/viewCart.do")) {

			if (!passport.isLogined()) {
				request.setAttribute("msg", "로그인이 필요합니다.");
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
			String user = passport.getUser().getId();

			List<CartVO> cartList = serviceC.cartListByUser(user);
			List<BookVO> booklistInCart = serviceC.bookListInCart(serviceB, cartList);

			request.setAttribute("bookList", booklistInCart); // 카트 정보 넘기기

			String page = "/cartView.jsp";
			getServletContext().getRequestDispatcher(page). // 흐름처리
					forward(request, response);
		}

		if (action.equals("/addCart.do")) {

			if (!passport.isLogined()) {
				request.setAttribute("msg", "로그인이 필요합니다.");
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}

			// request에서 입력한 값 받아오기;
			String req = request.getParameter("bookno");

			UserVO login = passport.getUser(); // 로그인 유저 정보 가져오기
			System.out.println("login 정보:" + login);

			if (req != null) {
				String user = login.getId();
				int bookno = Integer.parseInt(req);
				CartVO ex = serviceC.getcartByUseNBooks(user, bookno); // 이미 있는 책인지 확인
				if (ex != null) {
					request.setAttribute("msg", "🚨 이미 장바구니에 넣은 책입니다. 🚨"); // 카트 정보 넘기기
					System.out.println("이미 장바구니에 넣은 책입니다.");
					String page = "/viewBook.do?bookno=" + bookno;
					getServletContext().getRequestDispatcher(page). // 흐름처리
							forward(request, response);
					return;

				} else {
					CartVO vo = new CartVO();
					vo.setId(login.getId());
					vo.setBookno(bookno);
					serviceC.cartAdd(vo);
					response.sendRedirect("viewCart.do");
					return;
				}
			}

		}

		if (action.equals("/deleteCart.do")) {
			HttpSession session = request.getSession();

			String[] bookno = request.getParameterValues("bookno");

			if (!passport.isLogined()) {
				request.setAttribute("msg", "로그인이 필요합니다.");
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}

			String user = passport.getUser().getId();
			if (bookno != null) {
				for (int i = 0; i < bookno.length; i++) {
					int deleteNum = Integer.parseInt(bookno[i]);
					CartVO vo = serviceC.getcartByUseNBooks(user, deleteNum);
					System.out.println(vo);
					serviceC.cartDelete(vo.getCart_id());
				}
			}

			response.sendRedirect("viewCart.do");
			return;
		}

	}

}
