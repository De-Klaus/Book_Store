<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Каталог товаров</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		function addToCart(id) {
			var str = "id_good="+id;
			$.ajax({
				type:"post",
				url: "catalog",
				data: str,
				success: function(answer){
					$("#answer").html(answer);
				}
			});
		}
	</script>	
	<style>
		 h1,#b{
			text-align: center;
		}
		#answer{
			color:red;
		}
		td{
			vertical-align: top;
			padding-left:20px;
		}
		h2{
				background: linear-gradient(90deg, rgba(245, 235, 235, 0) 0%, rgba(243, 235, 235, 0.2) 25%, rgba(239, 239, 239, 0.2) 75%, rgba(243, 242, 242, 0) 100%);				
				color: rgba(8, 8, 8, 0.66);
				font-family: "Roboto";
				font-size: 15px;
				text-align: center;
				width: 100%;
				padding: 5px;
				}
	
	</style>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
	<div align="right">
				<%
				session = request.getSession();
				if(session.getAttribute("UserFio")==null){
					out.println("<script>alert('Для возможности добавления товара в корзину нужно авторизоваться')</script>");
				}%>
			</div>
	<h1 id="answer"></h1>
		<h1>Описание книги: <c:out value="${good.title}" /></h1>
		<table>
			<tr>
				<td><img width="400" src="images/<c:out value="${good.img}" />"></td>
				<td><c:out value="${good.info}"/><hr><div id="b">
				<button onclick="addToCart(<c:out value="${good.id}" />)"><h2>Купить</h2></button>
				<a href="catalog"><button><h2>Перейти в каталог</h2></button></a>
				</div></td>
			</tr>
		</table>			
		<hr>			
</body>
</html>