<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Каталог товаров</title>
	<style>
		table, td, h1{
			text-align: center;
		}
		h2{
			text-align: center;
			color: rgba(8, 8, 8, 0.66);
			text-shadow: 1px 1px 1px rgba(247, 241, 241, 0.53);
		}
		nav {
			max-width: 1254px;
		    mask-image: linear-gradient(90deg, rgba(241, 233, 233, 0.01) 0%, #f5eded 25%, #fbf4f4 75%, rgba(241, 235, 235, 0) 100%);
		    margin: 0 auto;
		    padding: 35px 0;
  			}
		nav ul {
			text-align: center;
	  		background: linear-gradient(90deg, rgba(245, 235, 235, 0) 0%, rgba(243, 235, 235, 0.2) 25%, rgba(239, 239, 239, 0.2) 75%, rgba(243, 242, 242, 0) 100%);
	  		width: 100%;
	  		box-shadow: 0 0 25px rgba(23, 22, 22, 0.15), inset 0 0 1px rgba(249, 245, 245, 0.63);
			}
		nav ul li {
  			display: inline-block;
			}

		nav ul li a {
			 padding: 18.9px;
			 font-family: "Roboto";
			 color: rgba(8, 8, 8, 0.66);
			 text-shadow: 1px 1px 1px rgba(247, 241, 241, 0.53);
			 font-size: 25px;
			 text-decoration: none;
			 display: block;
			 }
		header{
			font-size: 18px;
			color: rgba(8, 8, 8, 0.66);
			text-shadow: 1px 1px 1px rgba(247, 241, 241, 0.53);
		}
		
	</style>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
	<nav>
		<header>
			<div align="right">
				<%
				session = request.getSession();
				if(session.getAttribute("UserFio")!=null){
					String UserFio = (String) session.getAttribute("UserFio");
					out.println("<p class='hello'>Пользователь, "+UserFio+"!</p>");
				}%>
			</div>
		</header>
		<ul>
			<li><a href="myBookStore">Главная</a></li>
			<li><a href="catalog">Каталог товаров</a></li>
			<li><a href="feedback">Отзывы</a></li>
			
			<%session = request.getSession();
					if (session.getAttribute("UserFio")!=null){
						if(session.getAttribute("UserFio").equals("Администратор")){
							out.println("<li><a name='poisk' href='ord'>Заказы сайта</a></li>");
							out.println("<li><a name='poisk' href='Exit'>Выйти</a></li>");
						}
						else{
							out.println("<li><a name='poisk' href='bas'>Корзина</a></li>");
							out.println("<li><a name='poisk' href='Exit'>Выйти</a></li>");
						}
					}
					else{
						out.println("<li><a href='aut'>Войти</a></li>");
						out.println("<li><a href='reg'>Регистрация</a></li>");
					}
					%>	
		</ul>
	</nav>
	
	<h1>Добро пожаловать в книжный интернет-магазин Сlassics Books!</h1>
	
	<em><h2>У нас вы найдёте все классические произведения любых авторов, на ваш вкус. 
	Благодаря тому, что мы интернет-магазин и расположены в одном месте, мы можем 
	позволить себе закупать книги по оптовым ценам и продавать вам по ценам значительно 
	ниже рыночных. Удачных покупок!</h2></em>
	
	<p align="center"><img src="images/Houm.jpg" width=1000></p>
</body>
</html>