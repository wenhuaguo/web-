<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/15
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link href="/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<%@ include file="include/navbar.jsp"%>

<!--header-bar end-->
<div class="container">
    <div class="box">
        <div class="talk-item">
            <ul class="topic-type unstyled inline" style="margin-bottom:0px;">
                <li class="${empty param.nodeid ?'active':'' }"><a href="/home" >全部</a></li>
                <c:forEach items="${nodeList}" var="node">
                    <li class="${node.id == param.nodeid ? 'active':''}"><a href="/home?nodeid=${node.id}">${node.nodename}</a></li>
                </c:forEach>
            </ul>
        </div>
        <c:forEach items="${requestScope.pageList.items}" var="post">
            <div class="talk-item">
                <table class="talk-table">
                    <tr>
                        <td width="50">
                            <img class="avatar" src="${post.user.avatar}?imageView2/1/w/40/h/40" alt="">
                        </td>
                        <td width="80">
                            <a href="">${post.user.username}</a>
                        </td>
                        <td width="auto">
                            <a href="/showPost?postId=${post.id}">${post.title}</a>
                        </td>
                        <c:if test="${post.replynum > 0}">
                            <td width="50" align="center">
                                <a href="/showPost?postId=${post.id}"><span class="badge">${post.replynum}</span></a>
                            </td>
                        </c:if>
                    </tr>
                </table>
            </div>
        </c:forEach>

        <div class="pagination pagination-mini pagination-centered">
            <ul id="pagination" style="margin-bottom:20px;"></ul>
        </div>
    </div>
    <!--box end-->
</div>
<!--container end-->
<div class="footer">
    <div class="container">
        Copyright © 2016 kaishengit
    </div>
</div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function () {
        var nodeid = ${not empty param.nodeid} ? "&nodeid=${param.nodeid}":"";
        $("#pagination").twbsPagination({
            totalPages:${pageList.totalPage},
            visiblePages:5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href : '?p={{number}}'+ nodeid
        });
    });
</script>
</body>
</html>

