package BookInfo;

public class BookListDTO {
	
	int bookID;
	String bookName;
	String authorName;
	int price;
	boolean rentAble;
	int rentCount;
	String rentPeopleID;
	
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isRentAble() {
		return rentAble;
	}
	public void setRentAble(boolean rentAble) {
		this.rentAble = rentAble;
	}
	public int getRentCount() {
		return rentCount;
	}
	public void setRentCount(int rentCount) {
		this.rentCount = rentCount;
	}
	public String getRentPeopleID() {
		return rentPeopleID;
	}
	public void setRentPeopleID(String rentPeopleID) {
		this.rentPeopleID = rentPeopleID;
	}
	
	public BookListDTO() {
		
	}
	
	public BookListDTO(int bookID, String bookName, String authorName, int price, boolean rentAble, int rentCount,
			String rentPeopleID) {
		super();
		this.bookID = bookID;
		this.bookName = bookName;
		this.authorName = authorName;
		this.price = price;
		this.rentAble = rentAble;
		this.rentCount = rentCount;
		this.rentPeopleID = rentPeopleID;
	}
	
	
}
