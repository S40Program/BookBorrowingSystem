package domain;

import java.io.Serializable;

public class Student extends User implements Serializable{
	private BookBorrow borrow=new BookBorrow();
	private OrderHistory orderHistory=new OrderHistory();
	public Student() {}
	public Student(long id,String name,String password) {
		super(id,name,password);
	}
	public BookBorrow getBorrow() {
		return borrow;
	}
	public void setBorrow(BookBorrow borrow) {
		this.borrow = borrow;
	}
	public OrderHistory getOrderHistory() {
		return orderHistory;
	}
	public void setOrderHistory(OrderHistory orderHistory) {
		this.orderHistory = orderHistory;
	}
		
}

