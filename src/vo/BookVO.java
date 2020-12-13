package vo;

public class BookVO {
	private int bookno; // NUMBER(4) PRIMARY KEY,
	private String title; // VARCHAR2(40),
	private String publisher; // VARCHAR2(40),
	private int price; // NUMBER(8)
	private String img;
	private String detail;

	public BookVO() {

	}

	public BookVO(int bookno, String title, String publisher, int price, String img, String detail) {
		super();
		this.bookno = bookno;
		this.title = title;
		this.publisher = publisher;
		this.price = price;
		this.img = img;
		this.detail = detail;
	}


	public int getBookno() {
		return bookno;
	}

	public void setBookno(int bookno) {
		this.bookno = bookno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public void setDetail(String detail) {
		this.detail = detail;
	}
	

	public String getDetail() {
		return detail;
	}
	

	public String getFormattedDetail() {
		if(this.detail == null) {
			return "";
		}
		return this.detail.replaceAll("\n","<br/>");
	}

	@Override
	public String toString() {
		return "BookVO [bookno=" + bookno + ", title=" + title + ", publisher=" + publisher + ", price=" + price
				+ ", img=" + img + ", detail=" + detail + "]";
	}


}
