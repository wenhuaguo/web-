<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/28
  Time: 17:07
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
<%@ include file="../include/adminNavbar.jsp" %>
<!--header-bar end-->
<div class="container-fluid" style="margin-top:20px">
    <table class="table">
        <thead>
        <tr>
            <th>名称</th>
            <th>发布人</th>
            <th>发布时间</th>
            <th>回复数量</th>
            <th>最后回复时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach  items="${page.items}" var="post">
            <tr>
                <td>
                    <a href="/showPost?postId=${post.id}" target="_blank">${post.title}</a>
                </td>
                <td>${post.user.username}</td>
                <td>${post.createtime}</td>
                <td>${post.replynum}</td>
                <td>${post.lastreplytime}</td>
                <td>
                    <a href="javascript:;" rel="${post.id}" class="del">删除</a>
                </td>
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
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>
<script src="/static/js/sweetalter/sweetalert.min.js"></script>
<script>
    $(function () {
        $("#pagination").twbsPagination({
            totalPages:${page.totalPage},
            visiblePages:5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href : '?p={{number}}'
        });
        $(".del").click(function () {
            //获取当前主题的id
            var $postid = $(this).attr("rel");
            swal({title: "你确定要删除么？",
                        text: "你将不能够恢复这条信息！",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        closeOnConfirm: false },
                    function(){

                        $.ajax({
                            url:"/admin/post",
                            type:"post",
                            data:{"postId":$postid},
                            success:function (json) {
                                if(json.state == "success"){
                                    swal({title:"删除成功"},function () {
                                        //刷新当前页
                                        window.history.go(0);
                                    });
                                }else {
                                    swal(json.message);
                                }
                            },
                            error:function () {
                                swal("服务器异常");
                            }
                        });

        });
    });
    });
</script>
</body>
</html>

