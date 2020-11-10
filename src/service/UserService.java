package service;

import java.util.List;

import vo.UserVO;

public interface UserService {
	
	List<UserVO> userList();
	
	void userAdd(UserVO vo);
	
	void userDelete(String id);
	
	void userUpdate(UserVO vo);
	
	UserVO getUser(String id);
	
	List<UserVO> searchUser(String condition, String keyword);
	
}
