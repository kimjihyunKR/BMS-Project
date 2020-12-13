
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
		//default 정렬
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
				vo.setImg(rs.getString("img"));
				vo.setDetail(rs.getString("detail"));
				//리스트 뷰에서 이미지 뿌리지 x
				list.add(vo);
			}

		} catch (Exception e) {
			System.out.println("error : " + e);
		} finally {
			JDBCUtil.close(con, ps, rs);
		}

		return list;
	}

	public void bookAdd(BookVO vo) {
		String sql = "insert into book (title,publisher,price, img, detail) values (?,?,?,?,?)";

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
			ps.setString(4, vo.getImg());
			ps.setString(5, vo.getDetail());

			// 실행
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
		String sql = "UPDATE book SET title = ? , publisher=?, price = ? , img =? , detail=? WHERE bookno = ? "; // ?로 바인딩 보안상 좋음

		// SQL 구문 처리하기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅
			ps.setString(1, vo.getTitle() );
			ps.setString(2, vo.getPublisher());
			ps.setInt(3, vo.getPrice());
			ps.setString(4, vo.getImg());
			ps.setString(5, vo.getDetail());
			ps.setInt(6, vo.getBookno());

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
				vo.setBookno(rs.getInt("bookno"));
				vo.setPrice(rs.getInt("price"));
				vo.setPublisher(rs.getString("publisher"));
				vo.setTitle(rs.getString("title"));
				vo.setImg(rs.getString("img"));
				vo.setDetail(rs.getString("detail"));
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
				vo.setPublisher(rs.getString("publisher"));
				vo.setTitle(rs.getString("title"));
				vo.setImg(rs.getString("img"));
				vo.setDetail(rs.getString("detail"));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCUtil.close(con, ps, rs); // 자원반납은 필수
		}

		return vo;

	}

}
