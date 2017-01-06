<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/29
  Time: 22:40
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
    <link rel="stylesheet" href="/static/css/sweetalert.css">
</head>
<body>
<%@include file="../include/adminNavbar.jsp"%>
<!--header-bar end-->
<div class="container-fluid" style="margin-top:20px">
    <table class="table">
        <thead>
        <tr>
            <th>账号</th>
            <th>注册时间</th>
            <th>最后登录时间</th>
            <th>最后登录IP</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageList.items}" var="userVo">
            <tr>
                <td>${userVo.username}</td>
                <td>${userVo.createtime}</td>
                <td>${userVo.lastLoginTime}</td>
                <td>${userVo.loginIp}</td>
                <td><a href="javascript:;" class="update" rel="${userVo.userState},${userVo.userId}">
                ${userVo.userState == '1' ? '禁用':'恢复'}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination pagination-mini pagination-centered">
        <ul id="pagination" style="margin-bottom:20px;"></ul>
    </div>
</div>
<!--container end-->
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>
<script src="/static/js/sweetalter/sweetalert.min.js"></script>
<script>
    $(function () {
        $("#pagination").twbsPagination({
            totalPages:${pageList.totalPage},
            visiblePages:5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href : '?p={{number}}'
        });
        $(".update").click(function () {
            //获得a标签的rel属性对应的值
            var content = $(this).attr("rel");
            $.post("/admin/user",{"content":content},function (json) {
                if(json.state == "success"){
                    swal({title:"修改成功"},function () {
                        //刷新当前页面
                        window.history.go(0);
                    })

                }else {
                    swal(json.message);
                }
            });
        });
//        function aa(userId,userState) {
//            $.post("/admin/user",{},function (json) {
//                if(json.state == "success"){
//                    //刷新当前页面
//                    window.history.go(0);
//                }else {
//                    swal(json.message);
//                }
//            });
//        }
    });
</script>
</body>
</html>

