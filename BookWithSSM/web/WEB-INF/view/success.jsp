<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>SUCCESS 成功</h1>

<form>
    <tr>
        <td> 用户名称</td>
        <td> 用户密码</td>
        <td>用户邮箱</td>
    </tr>
    <br/>
    <c:forEach items="${requestScope.user}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.password}</td>
            <td>${user.email}</td>
        </tr>
        <br/>
    </c:forEach>

</form>
</body>
</html>
