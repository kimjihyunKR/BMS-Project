package service;

import java.util.List;

import dao.UserDAO;
import vo.UserVO;

public class UserServiceImpl implements UserService {
	
	private UserDAO dao = null;
	
	public UserServiceImpl() {}
	
	public UserServiceImpl(UserDAO dao) {
		this.dao = dao;
	}
	
	public UserDAO getDao() {
		return dao;
	}
	
	@Override
	public List<UserVO> userList() {
		return dao.userList();
	}

	@Override
	public void userAdd(UserVO vo) {
		dao.userAdd(vo);
	}

	@Override
	public void userDelete(String id) {
		dao.userDelete(id);
	}

	@Override
	public void userUpdate(UserVO vo) {
		dao.userUpdate(vo);
	}

	@Override
	public UserVO getUser(String id) {
		return dao.getUser(id);
	}

	@Override
	public List<UserVO> searchUser(String condition, String keyword) {
		return dao.userSearch(condition, keyword);
	}

}
