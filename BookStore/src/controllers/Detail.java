package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BaseModel;
import models.Good;

/**
 * Servlet implementation class Detail
 */
@WebServlet({ "/Detail", "/detail", "/det" })
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		try {
			Good good = BaseModel.getGood(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("good", good);//goods - это название переменной для jsp файла goods.jsp
			request.getRequestDispatcher("WEB-INF/views/goodDetail.jsp").forward(request, response);
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
