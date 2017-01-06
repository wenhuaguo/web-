<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/28
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/sweetalert.css" rel="stylesheet" >
    <style>
        .mt20 {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<%@ include file="../include/adminNavbar.jsp" %>
<!--header-bar end-->
<div class="container-fluid mt20">
    <a href="/admin/newnode" class="btn btn-success">添加新节点</a>
    <table class="table">
        <thead>
        <tr>
            <th>节点名称</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${nodeList}" var="node">
            <tr>
                <td>${node.nodename}</td>
                <td>
                    <a href="/admin/editNode?nodeId=${node.id}">修改</a>
                    <a href="javascript:;" class="del" rel="${node.id}">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!--container end-->
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/sweetalter/sweetalert.min.js"></script>
<script>
    $(function () {
        $(".del").click(function () {
            //获得a标签里面的rel属性对应的nodeId值
            var $nodeid = $(this).attr("rel");
            swal({
                        title: "你确定要删除么？",
                        text: "你将不能够恢复此节点",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        closeOnConfirm: false
                    },
                    function () {
                        $.post("/admin/node", {"nodeid":$nodeid}, function (json) {
                            if (json.state == "success") {
                                swal({title: "删除成功"}, function () {
                                    //刷新当前页面
                                    window.history.go(0);
                                });
                            } else {
                                swal(json.message);
                            }
                        });


                    });
        });
    });
</script>
</body>
</html>

