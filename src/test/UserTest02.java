package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.UserDAO_Mariadb;
import service.UserService;
import service.UserServiceImpl;
import vo.UserVO;

public class UserTest02 {
	UserService service;
	
	@BeforeEach
	void setUp() throws Exception {
		UserDAO_Mariadb dao = new UserDAO_Mariadb();
		service = new UserServiceImpl(dao);
	}
	
	@Test
	void list() {
		System.out.printf("%20s |%20s |%20s |%10s\n", "id", "pw", "name", "role" );
		System.out.println("--------------------------------------------------------------------------------------------------");
		service.userList().forEach(   i -> { 
			System.out.printf("%20s |%20s |%20s |%10s\n", i.getId(), i.getPassword(), i.getName(), i.getRole());
		});
	}
	
//	@Test
//	void add() {
//		UserVO vo = new UserVO();
//		vo.setId("root1234");
//		vo.setPassword("qwer1234");
//		vo.setName("lee admin");
//		vo.setRole("admin");
//		
//		service.userAdd(vo);
//	}
	
	
//	@Test
//	void delete() {
//		UserVO vo = service.getUser("react");
//		if(vo != null) {
//			System.out.println("before : " + vo);
//			service.userDelete(vo.getId());
//			System.out.println("gone...");
//		}
//	}
//	
	
	@Test
	void update() {
		UserVO vo = service.getUser("root");
		if(vo != null) {
			System.out.println(vo);
			vo.setName("JJJJ");
			vo.setPassword("qwerqwer");
			service.userUpdate(vo);
			System.out.println(service.getUser(vo.getId()));
		}
	}
	
//	@Test
//	void search() {
//		List<UserVO> list = service.searchUser("name", "lee");
//		list.forEach( i -> { System.out.println(i); });
//		
//	}
}
