<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Зарегистрироваться</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		function addСompare(login, password,fio) {
			if(login!="" && password!="" && fio!=""){
				var str = "registration="+login+" "+password+" "+fio;
			}
			else{
				alert("Вы заполнили не все поля!");
			}
			$.ajax({
				type:"post",
				url: "reg",
				data: str,
				success: function(answer){
					$("#answer").html(answer);
				}
			});
		}
		
	</script>
	<style>
		table, td, h1{
			text-align: center;
		}
		table, tr, th{
			border:0px solid; border-collapse:collapse;
		}
		#answer{
			color:red;
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
	    nav ul li a:hover {
			  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1), inset 0 0 1px rgba(255, 255, 255, 0.6);
			  background: rgba(255, 255, 255, 0.1);
			  color: rgba(0, 0, 0, 0.64);
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
					out.println("<p class='hello'>Уважаемый, "+UserFio+"! Авторизация прошла успешно!</p>");
				}
				else{
					
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
							//System.out.println("Hello");
							request.getRequestDispatcher("/WEB-INF/views/houme.jsp").forward(request, response);
						}
					}
					else{
						out.println("<li><a href='aut'>Войти</a></li>");
						out.println("<li><a href='reg'>Регистрация</a></li>");
					}
					%>	
		</ul>
	</nav>
	<!--<h1 id="answer"></h1>-->
	
	<table style="margin:0 auto;" width="50%">
		<tr>
			<th>ФИО<p></th>
			<th><input placeholder="Укажите фамилию, имя, отчество" name="fio" id="fio"><p></th>
		</tr>
		<tr>
			<th>Логин<p></th>
			<th><input placeholder="Укажите логин" name="log" id="log"><p></th>
		</tr>
		<tr>
				<th>Пароль<p></th>
				<th><input type="password" placeholder="Укажите пароль" name="pass" id="pass"><p></th>
		</tr>
		<tr>
				<th>
					<!--  <a href="catalog">Перейти в каталог</a>-->
				</th>
				<th><a href="reg"><button onclick='addСompare(document.getElementById("log").value,document.getElementById("pass").value,document.getElementById("fio").value)' type="submit">Зарегистрироваться</button></a></th>
		</tr>
	</table>
</body>
</html>