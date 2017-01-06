<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/15
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="header-bar">
    <div class="container">
        <a href="/home" class="brand">
            <i class="fa fa-reddit-alien"></i>
        </a>
            <%--<input style="margin-left: 200px" type="text" class="search-query" placeholder="输入关键字搜索" id="keywords">--%>


        <%--给一个隐藏域判断用户是否进行登录如果当前用户不为空那么就显示1这个是隐藏的--%>
            <span class="hide" id="islogin"><c:if test="${not empty curr_user}">1</c:if></span>
        <ul class="unstyled inline pull-right">
            <c:choose>
                <c:when test="${not empty sessionScope.curr_user}">
                    <li>
                        <a href="/set">
                            <img id="navabar_avatar" src="http://ohwsqq8z2.bkt.clouddn.com/${sessionScope.curr_user.avatar}?imageView2/1/w/20/h/20" class="img-circle" alt="">
                        </a>
                    </li>
                    <li>
                        <a href="/newPost"><i class="fa fa-plus">发帖</i></a>
                    </li>
                    <li>
                        <a href="/notify"><i class="fa fa-bell"><span class="badge badge-info" id="span"></span></i></a>
                    </li>
                    <li>
                        <a href="/set"><i class="fa fa-cog">设置</i></a>
                    </li>
                    <li>
                        <a href="/loginOut"><i class="fa fa-sign-out">退出</i></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="/login"><i class="fa fa-sign-in">登录</i></a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/user/notify.js"></script>
</body>
</html>
