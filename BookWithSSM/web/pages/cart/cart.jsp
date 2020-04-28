<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>

	<% String path = request.getContextPath();%>
	<link type="text/css" rel="stylesheet" href="<%=path%>/static/css/style.css" >
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="<%=path%>/static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<div>
				<span>欢迎<span class="um_span">${sessionScope.trueName}</span>光临尚硅谷书城</span>
				<a href="<%=path%>/showMyOrder/${sessionScope.trueName}">我的订单</a>
				<a href="<%=path%>/exit">注销</a>&nbsp;&nbsp;
				<a href="<%=path%>/index.jsp">返回</a>
			</div>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>订单号</td>
				<td>书名</td>
				<td>价格</td>
				<td>数量</td>
				<td>操作</td>
			</tr>

			<c:forEach items="${sessionScope.cartItem.carts}" var="cart">
				<tr>
					<td>${cart.id}</td>
					<td>${cart.bookName}</td>
					<td>${cart.totalPrice}</td>
					<td>${cart.count}</td>
					<td><a href="<%=path%>/deleteCart/${cart.id}">删除</a></td>
				</tr>
			</c:forEach>

			

			
		</table>
		
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.totalNum}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.totalMoney}</span>元</span>
			<span class="cart_span"><a href="<%=path%>/clearCart">清空购物车</a></span>
			<span class="cart_span"><a href="<%=path%>/setCount">去结账</a></span>
		</div>

	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>