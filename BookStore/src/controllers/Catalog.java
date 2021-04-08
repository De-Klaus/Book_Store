package controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.BaseModel;


@WebServlet({ "/Catalog", "/catalog" })
public class Catalog extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//Подготовим данные для отправки в шаблон
		try {
			if(request.getParameter("in") != null) {				
				request.getRequestDispatcher("WEB-INF/views/authorization.jsp").forward(request, response);
			}
			if(request.getParameter("reg") != null) {				
				request.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(request, response);
			}
			if(request.getParameter("goodsAutor") != null) {
				request.setAttribute("goods", BaseModel.getGoods());
				request.getRequestDispatcher("WEB-INF/views/goodsAutor.jsp").forward(request, response);
			}
			else {
				request.setAttribute("goods", BaseModel.getGoods());//goods - это название переменной для jsp файла goods.jsp
				request.getRequestDispatcher("WEB-INF/views/goods.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("id_good") != null) {
			try {
				if(BaseModel.addToCart(Integer.parseInt(request.getParameter("id_good")))){
					response.getWriter().print("Товар успешно добавлен в корзину!");
				}
				else {
					response.getWriter().print("Ошибка при добавлении товара в корзину!");
				}
			} catch (NumberFormatException | ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
		}	
	}

}
