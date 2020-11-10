package test;

import dao.UserDAO;
import vo.UserVO;

public class UserTexst01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDAO u =new UserDAO();
		
		UserVO vo = new UserVO();
		vo.setId("react");
		vo.setPassword("lee");
		vo.setName("kim nana");
		vo.setRole("user");
		
		u.userAdd(vo);
		
		System.out.println("---------all----------");
		u.userList().forEach( i -> { System.out.println(i); });	
	}

}
