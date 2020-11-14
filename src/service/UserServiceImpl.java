package service;

import java.util.List;

import dao.UserDAO_Mariadb;
import vo.UserVO;

public class UserServiceImpl implements UserService {
	
	// dao 주소가 기입되어야 돌아간다
	private UserDAO_Mariadb dao = null;
	
	public UserServiceImpl() {
		super();
	}
	
	public UserServiceImpl(UserDAO_Mariadb dao) {
		this.dao = dao;
	}
	
	public UserDAO_Mariadb getDao() {
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

	@Override
	public UserVO login(String id, String password) {
		return dao.login(id, password);
	}

	@Override
	public UserVO login(UserVO vo) {
		return dao.login(vo);
	}

}
