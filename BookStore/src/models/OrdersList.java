package models;

public class OrdersList {
	private String nUsers;
	private String nBooks;
	private int count;
	private int price;
	
	public String getnUsers() {
		return nUsers;
	}

	public void setnUsers(String nUsers) {
		this.nUsers = nUsers;
	}

	public String getnBooks() {
		return nBooks;
	}

	public void setnBooks(String nBooks) {
		this.nBooks = nBooks;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public OrdersList(String nUsers, String nBooks, int count, int price) {
		this.nUsers = nUsers;
		this.nBooks = nBooks;
		this.count = count;
		this.price = price;
	}
	
	
}
