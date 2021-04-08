<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Каталог товаров</title>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
	<h1>Каталог товаров</h1>
	<table width="50%" border="1px">
		<tr>
			<th>Номер товара</th>
			<th>Название товара</th>
			<th>Стоимость товара</th>
		</tr>
		<c:forEach var="good" items="${goods}" varStatus="num">
			<tr>
				<td>${num.count}</td>
				<td>${good.title}</td>
				<td>${good.price}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>