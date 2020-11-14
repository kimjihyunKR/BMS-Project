package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO_Mariadb;
import service.UserService;
import service.UserServiceImpl;
import vo.UserVO;

@WebServlet({ "/login.do", "/logout.do" })
public class LogInOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// loginout.do
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html:charset=utf-8");
		
		UserDAO_Mariadb dao = new UserDAO_Mariadb();
		UserService service = new UserServiceImpl(dao);
		
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		UserVO login = service.login(id, pw);
		System.out.println(login);

	}

	
	// login.do
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html:charset=utf-8");
		
		UserDAO_Mariadb dao = new UserDAO_Mariadb();
		UserService service = new UserServiceImpl(dao);
		
		doGet(request, response);
	}

}
