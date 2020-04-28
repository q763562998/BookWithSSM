<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%--	<meta name="content-type" content="text/html; charset=UTF-8">--%>
    <title>hello</title>
    <% String path = request.getContextPath();%>
    <link type="text/css" rel="stylesheet" href="<%=path%>/static/css/style.css" >
    <script type="text/javascript" src="<%=path%>/static/script/jquery-1.7.2.js"></script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="<%=path%>/static/img/logo.gif" >
    <span class="wel_word">书城</span>
    <div>

        <c:if test="${!empty sessionScope.trueName}">
            ${"欢迎"}
        </c:if>

        ${sessionScope.trueName}
<%--        				<%="hello2"%>--%>
        <c:if test="${empty sessionScope.trueName}">

            <a href="<%=path%>/pages/user/login.jsp">登录</a>
            <a href="<%=path%>/pages/user/regist.jsp">注册</a>
        </c:if>
        <a href="<%=path%>/beforeCart">购物车</a>
        <a href="<%=path%>/pages/manager/manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="" method="get">
                价格：<input id="min" type="text" name="min" value=""> 元 -
                <input id="max" type="text" name="max" value=""> 元
                <input type="submit" value="查询" />
            </form>
        </div>
        <div style="text-align: center">
            <span>您的购物车中有3件商品</span>
            <div>
                您刚刚将<span style="color: red">时间简史</span>加入到了购物车中
            </div>
        </div>

        <c:forEach items="${requestScope.book}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="<%=path%>/static/img/default.jpg" />
                </div>
                <div class="book_info">
                    <div class="book_name" name="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>

                    <div class="book_author" name="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price" name="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">${book.price}</span>
                    </div>

                    <div class="book_amount" name="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.number}</span>
                    </div>
<%--                    <div class="book_add">--%>
<%--                        <button>加入购物车</button>--%>
<%--                        <script type="text/javascript">--%>
<%--                            $(function () {--%>
<%--                                $("div.book_add").click(function () {--%>


<%--                                    // location.href="/BookProject/cartService?action=add&name="+name+"&id="+id+"&price="+price;--%>
<%--                                    location.href="<%=path%>/insertToCart";--%>
<%--                                });--%>
<%--                            });--%>
<%--                        </script>--%>
<%--                    </div>--%>
                    <form action="<%=path%>/insertToCart/${book.id}" method="post">

                        <input type="hidden" name="book_name" value="${book.name}">
<%--                        <input type="hidden" name="book_id" value="${book.id}">--%>
                        <input type="hidden" name="book_author" value="${book.author}">
                        <input type="hidden" name="book_price" value="${book.price}">
                        <input type="hidden" name="book_amount" value="${book.number}">
                        <input type="submit" value="加入购物车">
                    </form>
                </div>
            </div>
        </c:forEach>



    </div>
<div style="text-align: center" >
    <tr >
        <td colspan="10">
            ${requestScope.page}
        </td>

    </tr>
</div>


</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>