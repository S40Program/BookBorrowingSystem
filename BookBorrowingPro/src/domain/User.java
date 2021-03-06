package domain;

public class User {
	private long id;
	private String name;
	private String password;
	private OrderHistory orderHistory;
	public User() {}
	public User(long id,String name,String password) {
		this.id=id;
		this.name=name;
		this.password=password;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public OrderHistory getOrderHistory() {
		return orderHistory;
	}
	public void setOrderHistory(OrderHistory orderHistory) {
		this.orderHistory = orderHistory;
	}
	
}

