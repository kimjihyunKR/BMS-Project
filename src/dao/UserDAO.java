package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.UserVO;

public class UserDAO {

	// db에서 모든 user를 리스트 형태로 가져오기
	public List<UserVO> userList() {
		List<UserVO> list = new ArrayList<UserVO>();
		String sql = "select * from user order by id desc";

		// db연동
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				UserVO vo = new UserVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setRole(rs.getString("role"));
				list.add(vo);
			}

		} catch (Exception e) {
			System.out.println("error : " + e);
		} finally {
			JDBCUtil.close(con, ps, rs); // 자원반납
		}

		return list;
	}

	// db에 user추가하기
	public void userAdd(UserVO vo) {
		String sql = "insert into user (id,password,name, role) values (?,?,?,?)";
		// SQL 구문 처리하기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPassword());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getRole());

			row = ps.executeUpdate();

			if (row == 0) {
				throw new Exception("등록실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs); // 자원반납은 필수
		}
	}

	// db에서 user삭제하기
	public void userDelete(String id) {
		String sql = "delete from user where id = ?";
		// SQL 구문 처리하기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
	}

	// db에 있는 user의 비밀번호 변경하기
	public void userUpdate(UserVO vo) {
		String sql = "update user set password =?, name =? where id = ? ";
		// SQL 구문 처리하기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);

			// ? 값 세팅
			ps.setString(1, vo.getPassword());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
	}


	// db에 있는 user 정보 검색하기
	public List<UserVO> userSearch(String condition, String keyword) {
		String sql = "select * from user where " + condition + " like ?  order by id desc ";
		// SQL 구문 처리하기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<UserVO> list = new ArrayList<UserVO>();
		UserVO vo = null;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅
			ps.setString(1, "%" + keyword + "%");
			// rs에 담아주기
			rs = ps.executeQuery();

			while (rs.next()) {
				vo = new UserVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setRole(rs.getString("role"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
		return list;
	}

	// db에서 user가져오기
	public UserVO getUser(String id) {
		String sql = "select * from user where id = ? ";
		// SQL 구문 처리하기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserVO vo = null;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, id); // 값 세팅

			rs = ps.executeQuery();
			while (rs.next()) {
				vo = new UserVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setRole(rs.getString("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}

		return vo;
	}
}
