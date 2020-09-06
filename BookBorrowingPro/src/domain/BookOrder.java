package domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookOrder {
	private List<BorrowItem> borrowItems=new ArrayList<BorrowItem>();
	private Date date;
	public BookOrder() {
		date=new Date();
	}
	public String getFormatDate() {
		SimpleDateFormat f =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        return f.format(date);
	}
	public double getTotalPrice() {
		double s=0;
		for(BorrowItem item:borrowItems) {
			s+=item.getBook().getPrice();
		}
		return s;
	}
	public List<BorrowItem> getBorrowItems() {
		return borrowItems;
	}

	public void setBorrowItems(List<BorrowItem> borrowItems) {
		this.borrowItems = borrowItems;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}