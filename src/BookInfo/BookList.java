package BookInfo;

import java.util.ArrayList;

public class BookList {
	
	private ArrayList<Integer> bookIDList = new ArrayList<Integer>();
	private ArrayList<String> bookNameList = new ArrayList<String>();
	private ArrayList<String> authorNameList = new ArrayList<String>();
	private ArrayList<Integer> priceList = new ArrayList<Integer>();
	private ArrayList<Boolean> rentAbleList = new ArrayList<Boolean>();
	private ArrayList<Integer> rentCountList = new ArrayList<Integer>();
	public BookList() {
	}

	public void setBookID(int index, Integer bookID) {
		this.bookIDList.add(index, bookID);
	}
	public void setBookName (int index, String bookName) {
		this.bookNameList.add(index, bookName);
	}
	public void setAuthorName(int index, String authorName) {
		this.authorNameList.add(index, authorName);
	}
	public void setPrice(int index, Integer price) {
		this.priceList.add(index, price);
	}
	public void setRentAble(int index, Boolean rentAble) {
		this.rentAbleList.add(index, rentAble);
	}
	public void setRentCount(int index, Integer rentCount) {
		this.rentCountList.add(index, rentCount);
	}
	
	public Integer[] getBookID() {
		return bookIDList.toArray(new Integer[bookIDList.size()]);
	}
	public String[] getBookName() {
		return bookNameList.toArray(new String[bookNameList.size()]);
	}
	public String[] getAuthorName() {
		return authorNameList.toArray(new String[authorNameList.size()]);
	}
	public Integer[] getPrice() {
		return priceList.toArray(new Integer[priceList.size()]);
	}
	public Boolean[] getRentAble() {
		return rentAbleList.toArray(new Boolean[rentAbleList.size()]);
	}
	public Integer[] getRentCount() {
		return rentCountList.toArray(new Integer[rentCountList.size()]);
	}
	
	public int getListSize() {
		return bookIDList.size();
	}

}