package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.BaseModel;


@WebServlet({ "/Registration", "/registration", "/reg" })
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		request.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("registration") != null) {
			try {
				String fio = BaseModel.addRegistr(request.getParameter("registration"));
				if(fio!=null) {
					response.getWriter().print("Вы успешно зарегистрировались!");
					HttpSession session = request.getSession();
					session.setAttribute("UserFio", fio);
				}
				else {
					System.out.println("Hello");
					response.getWriter().print("<script>alert('Пользовтель с таким логином уже есть! Замените логин.')</script>");
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
