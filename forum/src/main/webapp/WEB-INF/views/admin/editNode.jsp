<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/28
  Time: 23:24
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
    <link rel="stylesheet" href="/static/css/sweetalert.css">
</head>
<body>
<%@ include file="../include/adminNavbar.jsp" %>
<!--header-bar end-->
<div class="container-fluid" style="margin-top:20px">
    <form action="" id="saveForm">
        <legend>编辑节点</legend>
        <label>节点名称</label>
        <input type="hidden" value="${node.id}" name="nodeid">
        <input type="text" value="${node.nodename}" name="nodename">
        <div class="form-actions">
            <button class="btn btn-primary" id="saveBtn" type="button">保存</button>
        </div>
    </form>
</div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/js/sweetalter/sweetalert.min.js"></script>
<script>

    $(function () {
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        });
        $("#saveForm").validate({
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
                $.ajax({
                    url:"/admin/editNode",
                    type:"post",
                    data:$(form).serialize(),
                    success:function (json) {
                        if(json.state == "success"){
                            window.location.href = "/admin/node";
                        }else {
                            swal(json.message);
                        }
                    },
                    error:function () {
                        swal("服务器异常");
                    }
                });

            }
        });
    });

</script>
<!--container end-->
</body>
</html>

