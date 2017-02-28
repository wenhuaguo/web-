
<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2017/1/10
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <c:if test="${not empty message}">
            <div class="alter alert-success">
                ${message}
            </div>
        </c:if>
        <a href="/user/add" class="btn btn-primary">添加</a>
        <table class="table">

            <tr>
                <th>姓名</th>
                <th>年龄</th>
            </tr>
            <c:forEach items="${accountList}" var="account">
                <tr>
                    <td>${account.userName}</td>
                    <td>${account.age}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
