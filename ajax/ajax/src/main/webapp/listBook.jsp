<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/7
  Time: 17:14
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
    <a href="/add" class="btn btn-success">添加书籍</a>
    <a href="/logout" class="btn btn-default">安全退出</a>
    <table class="table">
        <thead>
        <tr>
            <th>书籍名称</th>
            <th>作者</th>
            <th>出版社</th>
            <th>ISBN</th>
            <th>总数量</th>
            <th>当前数量</th>
            <th>#</th>
        </tr>
        </thead>
        <tbody>
            <c:if test="${empty list}">
                <tr>
                    <td colspan="6">暂无数据</td>
                </tr>
            </c:if>
            <c:forEach items="${list}" var="book">
                <tr>
                    <td>${book.bookname}</td>
                    <td>${book.author}</td>
                    <td>${book.publish}</td>
                    <td>${book.isbn}</td>
                    <td>${book.total}</td>
                    <td>${book.nownums}</td>
                    <td><a href="javascript:;" class="del" rel="${book.id}">删除</a>
                        <a href="/edit?id=${book.id}">  修改</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

    <script src="/static/js/jquery-1.11.3.min.js"></script>
    <script src="/static/js/ajax.js"></script>
    <script>
        $(function () {
            $(".del").click(function () {
                if(confirm("确定要删除么")){
                    //获得rel属性的值
                    var id = $(this).attr("rel");
                    //根据id向目标跳转
                    window.location.href = "/del?id?" + id;
                }
            });
        });
//        $(function () {
//            var $body = $("tbody");
//            $.ajax({
//                url : "/list",
//                type : "get",
//                success : function (data) {
//                    for(var i = 0; i < data.length; i++){
//                        var book = data[i];
//                        var  jsp = "<tr><td>"+book.bookname+"</td><td>"+book.author+"</td><td>"+book.publish+"</td><td>"+book.isbn+"</td><td>"+book.total+"</td><td>"+book.nownums+"</td><td><a>删除</a><a>  修改</a></td></tr>";
//                        $(jsp).appendTo($body);
//                    }
//                },
//                error : function () {
//                    alert("服务器异常");
//                }
//            });
//        });
    </script>
</body>
</html>
