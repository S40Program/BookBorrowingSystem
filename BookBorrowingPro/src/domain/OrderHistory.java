package domain;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
	private List<BookOrder> bookorders=new ArrayList<BookOrder>();

	public List<BookOrder> getBookorders() {
		return bookorders;
	}

	public void setBookorders(List<BookOrder> bookorders) {
		this.bookorders = bookorders;
	}

	
	
}

