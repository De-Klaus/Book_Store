package models;

public class Cart {
	private String title;
	private int price, count, id_good;
	
	public Cart(String title, int price, int count, int id_good) {
		this.title = title;
		this.price = price;
		this.count = count;
		this.id_good = id_good;
	}

	public String getTitle() {
		return title;
	}

	public int getId_good() {
		return id_good;
	}

	public void setId_good(int id_good) {
		this.id_good = id_good;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
}
