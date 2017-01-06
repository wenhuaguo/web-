<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2017/1/2
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="navbar navbar-inverse  navbar-static-top">
    <div class="navbar-inner">
        <a class="brand" href="#">BBS 管理系统</a>
        <ul class="nav">
            <li class="active"><a href="#">首页</a></li>
            <li><a href="#">主题管理</a></li>
            <li><a href="#">节点管理</a></li>
            <li><a href="#">用户管理</a></li>
        </ul>
        <ul class="nav pull-right">
            <li><a href="">安全退出</a></li>
        </ul>
    </div>
</div>
<!--header-bar end-->

<div>


</div>
<div class="container-fluid" style="margin-top:20px">
    <table class="table">
        <thead>
        <tr>
            <th>日期</th>
            <th>新主题数</th>
            <th>新回复数</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${postList.items}" var="post">
            <tr>
                <td>${post.date}</td>
                <td>${post.newPostCount}</td>
                <td>${post.newReplyCount}</td>
                <td><a href="">详情</a></td>>
            </tr>
        </c:forEach>
        <tr>
            <td>
                2016-12-28
            </td>
            <td>123</td>
            <td>2546</td>

            <td>
                <a href="">详情</a>
            </td>
        </tr>
        <tr>
            <td>
                2016-12-27
            </td>
            <td>123</td>
            <td>2546</td>

            <td>
                <a href="">详情</a>
            </td>
        </tr>
        <tr>
            <td>
                2016-12-26
            </td>
            <td>123</td>
            <td>2546</td>

            <td>
                <a href="">详情</a>
            </td>
        </tr>
        <tr>
            <td>
                2016-12-25
            </td>
            <td>123</td>
            <td>2546</td>

            <td>
                <a href="">详情</a>
            </td>
        </tr><tr>
            <td>
                2016-12-24
            </td>
            <td>123</td>
            <td>2546</td>

            <td>
                <a href="">详情</a>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="pagination pull-right">
        <ul>
            <li><a href="#">Prev</a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">Next</a></li>
        </ul>
    </div>
</div>
<!--container end-->
</body>
</html>

