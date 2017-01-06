<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/29
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <%@ include file="../include/adminNavbar.jsp" %>
</div>
<!--header-bar end-->
<div class="container-fluid" style="margin-top:20px">
    <form action="" id="newNodeForm">
        <legend>添加新节点</legend>
        <label>节点名称</label>
        <input type="text" name="nodename" id="newnode">
        <div class="form-actions">
            <button class="btn btn-primary" type="button" id="newnodeBtn">保存</button>
        </div>
    </form>
</div>
<!--container end-->
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script>
    $(function () {
        $("#newnodeBtn").click(function () {
           $("#newNodeForm").submit();
        });
        $("#newNodeForm").validate({
            errorElement:'span',
            errorClass:'text-error',
            rules:{
                nodename:{
                    required:true,
                    remote:"/admin/nodeNameValidate"
                }
            },
            messages:{
                nodename:{
                    required:"内容不能为空",
                    remote:"该节点已存在"
                }
            },
            submitHandler:function (form) {
                $.post("/admin/newnode",$(form).serialize(),function (data) {
                    if(data == "success"){
                        window.location.href = "/admin/node";
                    }
                });
            }
        });
    });
</script>
</body>
</html>

