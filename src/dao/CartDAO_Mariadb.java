package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.CartVO;

public class CartDAO_Mariadb {

	// 전체 장바구니 불러오기
	public List<CartVO> cartList() {
		List<CartVO> list = new ArrayList<CartVO>();
		String sql = "SELECT * FROM cart ORDER BY bookno";
		// db연동
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				CartVO vo = new CartVO();
				vo.setCart_id(rs.getInt("cart_id"));
				vo.setBookno(rs.getInt("bookno"));
				vo.setId(rs.getString("id"));
				list.add(vo);
			}

		} catch (Exception e) {
			System.out.println("cartList error : " + e);
		} finally {
			JDBCUtil.close(con, ps, rs);
		}

		return list;

	}

	// 특정 user id의 장바구니 불러오기
	public List<CartVO> cartListByUser(String id) {
		List<CartVO> list = new ArrayList<CartVO>();
		String sql = "SELECT * FROM cart WHERE id=? ORDER BY bookno";
		// db연동
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				CartVO vo = new CartVO();
				vo.setCart_id(rs.getInt("cart_id"));
				vo.setBookno(rs.getInt("bookno"));
				vo.setId(rs.getString("id"));
				list.add(vo);
			}

		} catch (Exception e) {
			System.out.println("cartListByUser error : " + e);
		} finally {
			JDBCUtil.close(con, ps, rs);
		}

		return list;

	}

	// 카트 아이디로 가져오기
	public CartVO getCart(int cart_id) {

		String sql = "SELECT * FROM cart WHERE cart_id=? ORDER BY bookno";

		// db연동
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CartVO vo = null;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setInt(1, cart_id);

			rs = ps.executeQuery();

			while (rs.next()) {
				vo = new CartVO();
				vo.setCart_id(rs.getInt("cart_id"));
				vo.setBookno(rs.getInt("bookno"));
				vo.setId(rs.getString("id"));
			}

		} catch (Exception e) {
			System.out.println("getCart error : " + e);
		} finally {
			JDBCUtil.close(con, ps, rs);
		}

		return vo;
	}

	// 장바구니에 추가하기
	public void cartAdd(CartVO vo) {
		String sql = "INSERT INTO cart (id, bookno) VALUES (?,?)";
		// db연동
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, vo.getId());
			ps.setInt(2, vo.getBookno());

			row = ps.executeUpdate();

			if (row == 0) {
				throw new Exception("등록실패");
			}

		} catch (Exception e) {
			System.out.println("cartAdd error : " + e);
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
	}

	// 장바구니 아이템 제거
	public void cartDelete(int cart_id) {
		String sql = "DELETE FROM cart WHERE cart_id=?";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setInt(1, cart_id);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCUtil.close(con, ps, rs); // 자원반납은 필수
		}
	}

	// 수량 변경
	public void cartUpdate(int cart_no) {

	}

	// bookno search
	public CartVO getcartByUseNBooks(String user, int bookno) {
		List<CartVO> list = new ArrayList<CartVO>();
		String sql = "SELECT * FROM cart WHERE id='"+ user + "' AND bookno=? ORDER BY cart_id";

		// db연동
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CartVO vo = null;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setInt(1, bookno);

			rs = ps.executeQuery();

			while (rs.next()) {
				vo = new CartVO();
				vo.setCart_id(rs.getInt("cart_id"));
				vo.setBookno(rs.getInt("bookno"));
				vo.setId(rs.getString("id"));
			}

		} catch (Exception e) {
			System.out.println("getCart error : " + e);
		} finally {
			JDBCUtil.close(con, ps, rs);
		}

		return vo;
	}
}
