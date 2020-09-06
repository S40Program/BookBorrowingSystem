package domain;

import java.util.ArrayList;
import java.util.List;

public class BookBorrow {
	private List<BorrowItem> borrowItems=new ArrayList<BorrowItem>();

	public List<BorrowItem> getBorrowItems() {
		return borrowItems;
	}

	public void setBorrowItems(List<BorrowItem> borrowItems) {
		this.borrowItems =borrowItems;
	}
	public int getItemsSize() {
		return borrowItems.size();
	}
	public BorrowItem findItem(Book book) {
		for(BorrowItem item:borrowItems) {
			if(item.getBook().getId()==book.getId()) {
				return item;
			}
		}
		return null;
	}
	public void addBorrowItem(Book book,long number) {
		BorrowItem item=findItem(book);		
		if(item==null) {
			borrowItems.add(new BorrowItem(book,number));
		}else {
			item.setQuantity(item.getQuantity()+number);
		}
	}
}
