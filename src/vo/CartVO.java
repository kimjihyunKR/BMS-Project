package vo;

public class CartVO {
	
	private int cart_id;
	private String id; //user id
	private int bookno;
	
	public CartVO() {
		
	}
	
	public CartVO(int cart_id, String id, int bookno) {
		super();
		this.cart_id = cart_id;
		this.id = id;
		this.bookno = bookno;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBookno() {
		return bookno;
	}

	public void setBookno(int bookno) {
		this.bookno = bookno;
	}

	@Override
	public String toString() {
		return "Cart [cart_id=" + cart_id + ", id=" + id + ", bookno=" + bookno + "]";
	}
	
	
}
