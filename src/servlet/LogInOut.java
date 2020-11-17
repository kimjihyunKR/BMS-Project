package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO_Mariadb;
import service.UserService;
import service.UserServiceImpl;
import vo.UserVO;

@WebServlet({ "/login.do", "/logout.do" })
public class LogInOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// loginout.do
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html:charset=utf-8");

		HttpSession session = request.getSession();
		
		if( session != null ) {
			session.invalidate();
		}
		response.sendRedirect("/index.jsp");

	}

	// login.do
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html:charset=utf-8");

		UserDAO_Mariadb dao = new UserDAO_Mariadb();
		UserService service = new UserServiceImpl(dao);

		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		UserVO login = service.login(id, pw);
//		System.out.println(login);
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
	}
}
