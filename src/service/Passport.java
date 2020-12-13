package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.UserVO;

public class Passport {
	
	private HttpServletRequest request;
	private UserVO user = null;
	
	public Passport(HttpServletRequest request) {
		this.request = request;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}
	
	public boolean isLogined() {
		HttpSession session = this.request.getSession();
		UserVO login = (UserVO) session.getAttribute("login");

		if (login == null) {
			return false;
		}
		this.user = login;
		return true;
	}
	
}
