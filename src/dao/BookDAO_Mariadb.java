
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.BookVO;

public class BookDAO_Mariadb {

	public List<BookVO> bookList() {
		List<BookVO> list = new ArrayList<BookVO>();
		String sql = "select * from book order by bookno asc";

		// db연동
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				BookVO vo = new BookVO();
				vo.setBookno(rs.getInt("bookno"));
				vo.setPrice(rs.getInt("price"));
				vo.setPublisher(rs.getString("publisher"));
				vo.setTitle(rs.getString("title"));
				list.add(vo);
			}

		} catch (Exception e) {
			System.out.println("error : " + e);
		} finally {
			JDBCUtil.close(con, ps, rs);
		}

		return list;
	}

	public int insertBookList() {
		String sql = "insert into book (bookno ,title,publisher,price) " + "values ('title', 'publisher', 900)";

		// SQL 구문 처리하기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅

			// 실행
			// ps.executeQuery();
			// ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCUtil.close(con, ps, rs); // 자원반납은 필수
		}

		return row;
	}

	public void bookAdd(BookVO vo) {
		String sql = "insert into book (title,publisher,price) values (?,?,?)";
		// insert into book (title,publisher,price) values ('jsp','jihyun',900);

		// SQL 구문 처리하기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getPublisher());
			ps.setInt(3, vo.getPrice());

			// 실행
			// ps.executeQuery();
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

	public void bookDelete(int bookno) {
		String sql = "delete from book where bookno = ?";

		// SQL 구문 처리하기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅
			ps.setInt(1, bookno);

			// 실행
			// ps.executeQuery();
			ps.executeUpdate();

			// 결과값 처리

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCUtil.close(con, ps, rs); // 자원반납은 필수
		}

	}

	public void bookUpdate(BookVO vo) {
		String sql = "update book set price = ? where bookno = ? "; // ?로 바인딩 보안상 좋음

		// SQL 구문 처리하기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅
			ps.setInt(1, vo.getPrice());
			ps.setInt(2, vo.getBookno());

			// 실행
			// ps.executeQuery();
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs); // 자원반납은 필수
		}

	}

	public List<BookVO> bookSearch(String condition, String keyword) {
		String sql = "select * from book where " + condition + " like ?  order by bookno desc ";

		// SQL 구문 처리하기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BookVO> list = new ArrayList<BookVO>();

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅
			ps.setString(1, "%" + keyword + "%");

			// 실행
			rs = ps.executeQuery(); // rs에 담아주기

			// 결과값 처리
			while (rs.next()) {
				BookVO vo = new BookVO();
				vo = new BookVO();
				vo.setBookno(rs.getInt("bookno")); // rs에서 꺼내주기
				vo.setPrice(rs.getInt("price"));
				vo.setTitle(rs.getString("title"));
				vo.setPublisher(rs.getString("publisher"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCUtil.close(con, ps, rs); // 자원반납은 필수
		}

		return list;
	}

	public BookVO getBook(int bookno) {
		String sql = "select * from book where bookno = ? "; // ?로 바인딩 보안상 좋음

		// SQL 구문 처리하기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		BookVO vo = null;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅
			ps.setInt(1, bookno);

			// 실행
			rs = ps.executeQuery();

			// 결과값 처리
			while (rs.next()) {
				// 데이터가 있다면
				vo = new BookVO();
				vo.setBookno(rs.getInt("bookno"));
				vo.setPrice(rs.getInt("price"));
				vo.setTitle(rs.getString("title"));
				vo.setPublisher(rs.getString("publisher"));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCUtil.close(con, ps, rs); // 자원반납은 필수
		}

		return vo;

	}

}
