<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <% String path = request.getContextPath();%>
    <link type="text/css" rel="stylesheet" href="<%=path%>/static/css/style.css">
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <div>
        <c:if test="${empty requestScope.managerBook}">
            <a href="pages/manager/book_manager.jsp">图书管理</a>

        </c:if>
        <a href="<%=path%>/pages/manager/order_manager.jsp">订单管理</a>
        <a href="<%=path%>/index.jsp">返回商城</a>
    </div>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>

            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>

        <c:forEach items="${requestScope.managerBook}" var="book">

            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>

                <td>${book.number}</td>
                <td><a href="<%=path%>/update/${book.id}">修改</a></td>
                <td><a href="<%=path%>/deleteBook/${book.id}">删除</a></td>
            </tr>
        </c:forEach>


        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="<%=path%>/pages/manager/book_insert.jsp">添加图书</a></td>
        </tr>
    </table>
</div>
<div style="text-align: center" >
    <tr >
        <td colspan="10">
            ${requestScope.managerPage}
        </td>

    </tr>
</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>