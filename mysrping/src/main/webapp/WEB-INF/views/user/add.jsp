<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2017/1/10
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--如果没有写action的话会请求会提交到当前路径--%>
    <h1>你好spring MVC</h1>
    <form  method="post">
        <input type="text" name="userName">
        <input type="text" name="age">
        <input type="text" name="address">
        <button>save</button>
    </form>
</body>
</html>
