package domain;

public class BorrowItem {
	private Book book;
	private long quantity;
	public BorrowItem() {		
	}
	public BorrowItem(Book book,long quantity) {
		this.book=book;
		this.quantity=quantity;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	
}
