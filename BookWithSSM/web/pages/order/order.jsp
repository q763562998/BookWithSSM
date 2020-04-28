<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<% String path = request.getContextPath();%>
	<link type="text/css" rel="stylesheet" href="<%=path%>/static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<div>
				<span>欢迎<span class="um_span">${sessionScope.trueName}</span>光临尚硅谷书城</span>
<%--				<a href="<%=path%>">我的订单</a>--%>
				<a href="<%=path%>/exit">注销</a>&nbsp;&nbsp;
				<a href="<%=path%>/index.jsp">返回</a>
			</div>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>订单号</td>
				<td>书名</td>
				<td>总价</td>
				<td>数量</td>
			</tr>		
			<c:forEach items="${requestScope.orderList}" var="order">
				<tr>



					<c:forEach items="${order.cartItemList}" var="cart">
						<tr>
							<td>${order.dateTime}</td>
							<td>${order.orderNumber}</td>
							<td>${cart.bookName}</td>
							<td>${cart.totalPrice}</td>
							<td>${cart.count}</td>
						</tr>

					</c:forEach>

				</tr>

			</c:forEach>
		</table>
		
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>