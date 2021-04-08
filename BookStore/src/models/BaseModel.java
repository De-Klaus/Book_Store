package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BaseModel {
	private static ArrayList<Good> goods = new ArrayList<Good>(); //колекция товара
	private static ArrayList<Cart> basket = new ArrayList<Cart>(); //колекция товара в корзине
	private static ArrayList<Users> users = new ArrayList<Users>(); //пользователи
	private static ArrayList<OrdersList> orders = new ArrayList<OrdersList>(); //колекция пользоватлей заказавщих товары
	
	private static ArrayList<String> logins = new ArrayList<String>(); //колекция существующих логинов для предотвращения повторений
	
	private static int idUsers; //id зарегистрировавшегося или авторизовавшегося пользователя 
	
	static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(Config.driver);
		String url = "jdbc:mysql://localhost/" + Config.db;// настройка подключения
		return DriverManager.getConnection(url, Config.login, Config.pass);
	}
	
	public static ArrayList<Good> getGoods() throws ClassNotFoundException, SQLException{//метод для вывода данных в каталог из БД
		var c = getConnection();
		var ps = c.prepareStatement("select * from goods");
		ResultSet rs = ps.executeQuery();
		int id,price;
		String title,img,info;
		goods.clear();
		while(rs.next()) {
			id = rs.getInt("id");
			price = rs.getInt("price");
			title = rs.getString("title");
			img = rs.getString("images");
			info = rs.getString("text");
			goods.add(new Good(id,title,price,img,info));
		}
		return goods;
	}
	
	public static String addRegistr(String registr) throws SQLException, ClassNotFoundException { //проверка данных при регистрации и защита от повторения логина
		var c = getConnection();
		var ps = c.prepareStatement("select * from users");
		ResultSet rs = ps.executeQuery();
		logins.clear();
		int initId=0;//id максимальное для след. записи
		while(rs.next()) {
			logins.add(rs.getString("login"));
			initId = Integer.parseInt(rs.getString("id"));
		}
		idUsers = initId+1; //id текушего пользователя для записи его заказов в карзину
		System.out.println(idUsers + " текущие значение id пользователя");
		String[] words = registr.split(" ");
		boolean is = true; 
		for(String str:logins) {
			if(str.equals(words[0])) {
				is=false;
			}
		}
		if(is) {
			String delete = words[0]+" "+words[1]+" ";
			String fios = registr.replace (delete, "");
			var insert = "insert into users(login, password, fio) values('"+words[0]+"','"+words[1]+"','"+fios+"')";
			var readyInsert = c.prepareStatement(insert);			
			if(readyInsert.executeUpdate() > 0) {
				return fios;
			}
		}
		else {
			System.out.println("Такой логин уже существует, замените его");
			return null;
		}
		return null;
	
	}
	
	public static String author(String loginPassword) throws SQLException, ClassNotFoundException {//авторизация пользователя с проверкой пользователя
		var c = getConnection();
		var ps = c.prepareStatement("select * from users");
		ResultSet rs = ps.executeQuery();
		int id;
		String log, pas, fio;
		users.clear();
		while(rs.next()) {
			id = rs.getInt("id");
			log = rs.getString("login");
			pas = rs.getString("password");
			fio = rs.getString("fio");
			users.add(new Users(id,log,pas,fio));
		}
		for (Users strg : users) {
			String res = strg+"";
			if (res.equals(loginPassword)) {
				System.out.println("Данные найдены "+res);
				idUsers = strg.getId();
				return strg.getFio();
            }
			else {
				System.out.println("Ошибка авторизации");
			}
        } 	
		return null;
	}
	
	public static boolean addToCart(int idGood) throws SQLException, ClassNotFoundException {//добавление купленного товара в БД и суммирование одинаковых
		var c = getConnection();
		System.out.println(idUsers);
		var sql = "select * from basket where id_good="+idGood + " and id_users="+idUsers;
		var ps = c.prepareStatement(sql);
		ResultSet rs2 = ps.executeQuery();
		if(rs2.next()) {
			String delete = "select * from basket";
			String wereIs = sql.replace (delete, "");
			var update = "update basket set count=count+1"+wereIs;
			System.out.println(update + " суммирование");
			var readyUpdate = c.prepareStatement(update);
			if(readyUpdate.executeUpdate() > 0) {
				return true;
			}
		}
		else {
			var insert = "insert into basket(id_good,id_users,count) values("+idGood+","+idUsers+",1)";
			System.out.println(insert);
			var readyInsert = c.prepareStatement(insert);
			if(readyInsert.executeUpdate() > 0) {
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<Cart> getCart() throws ClassNotFoundException, SQLException{//получение содержания карзины для вывода на экран
		var c = getConnection();
		var ps = c.prepareStatement("select g.title as 'title',g.price as 'price', b.count, b.id_good from goods g inner join basket b on b.id_good=g.id where b.id_users=" + idUsers);
		ResultSet rs = ps.executeQuery();
		int price, count, id_good;
		String title;
		basket.clear();
		while(rs.next()) {
			price = rs.getInt("price");
			title = rs.getString("title");
			count = rs.getInt("count");
			id_good = rs.getInt("id_good");
			basket.add(new Cart(title,price, count, id_good));
		}
		return basket;
	}
	
	public static Good getGood(int id) throws ClassNotFoundException, SQLException{//данные для вывода подробной информации о товаре
		var c = getConnection();
		var ps = c.prepareStatement("select * from goods where id = "+id);
		ResultSet rs = ps.executeQuery();
		String title,img,info;
		int price;
		
		if(rs.next()) {
			price = rs.getInt("price");
			title = rs.getString("title");
			img = rs.getString("images");
			info = rs.getString("text");
			return new Good(id,title,price,img,info);
		}
		return null;
	}
	
	public static void removePosition(int id_good) throws ClassNotFoundException, SQLException {//удаление позиций из корзины
		var c = getConnection();
		var psRem = c.prepareStatement("delete from basket where id_good = "+id_good + " and id_users = " + idUsers);
		psRem.executeUpdate();
	}
	
	public static ArrayList<OrdersList> getOrders() throws ClassNotFoundException, SQLException{//получения данных для администратора о всех пользователях
		var c = getConnection();
		var ps = c.prepareStatement("select g.fio as 'users', r.title as 'books',  b.count as 'count', r.price as 'price' from users g, goods r inner join basket b where b.id_users=g.id and b.id_good=r.id");
		ResultSet rs = ps.executeQuery();
		String nUsers, nBooks;
		int count, price;
		orders.clear();
		while(rs.next()) {
			nUsers = rs.getString("users");
			nBooks = rs.getString("books");
			count = rs.getInt("count");
			price = rs.getInt("price");
			orders.add(new OrdersList(nUsers, nBooks, count, price));
		}
		return orders;
	} 
}
