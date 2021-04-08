package models;

public class Good {
	private int id;
	private String title;
	private int price;
	private String img;
	private String info;
	
	public String getImg() {
		return img;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
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
	public Good(int id, String title, int price,String img,String info) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.img = img;
		this.info = info;
	}
	
	@Override
	public String toString() {
		return "Товар "+title + " стоит "+price;
		
	}
}
