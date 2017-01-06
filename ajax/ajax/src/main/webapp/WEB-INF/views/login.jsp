<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/8
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/font-awesome.min.css">
</head>
<body>
<div class="container">
    <form id="loginForm">
        <div class="form-group">
            <label>账号</label>
            <input type="text" class="form-control" name="userName">
        </div>
        <div class="form-group">
            <label>密码</label>
            <input type="password" class="form-control" placeholder="password" name="password">
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="button" id="btn">登录</button>
        </div>
    </form>
</div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/validate/jquery.validate.min.js"></script>
<script>
    //alert($("#loginForm").serialize());
    $(function () {
//        $("#btn").click(function () {
//            $("#loginForm").submit();
//        });
        $("#btn").click(function () {
            var userName = $("userName").val();
            var password = $("password").val();
            $.post("/login",$("#loginForm").serialize()).done(function (data) {
                if(data.state == "error"){
                    alert(data.message);
                }else {
                    window.location.href = "/list";
                }
            }).error(function () {
                alert("服务器异常");
            })
        });
//        $("#loginForm").validate({
//            errorElement : "span",//使用什么标签标记错误，默认是label键值对
//            errorClass : "text-danger",//指定错误提示的css类名，可以自定义提示的样式
//            rules : {//自定义规则，key:value的形式，key要验证的元素，value可以是字符串或者对象
//                userName :{
//                    required : true   //required : true 必输字段
//                },
//                    password : {
//                    required : true  //required ： true 必输字段
//                 }
//                },
//            messages : {//自定义的提示信息，key:value,key要验证的元素，value是字符串或函数
//                userName : {
//                    required : "请输入账号"
//                },
//                password : {
//                    required : "请输入密码"
//                }
//
//            },
//            submitHandler : function () {//通过验证后运行的函数，里面要加上表单提交的函数，否则表单不会提交
//                $.ajax({
//                    url : "/login",
//                    type : "post",
//                    data : $("#loginForm").serialize(),//serialize()方法将form表单所有name属性的值作为键，内容作为值提取出来
//                    success : function (data) {
//                        if(data.state == "error"){
//                            alert(data.message);
//                        }else {
//                            window.location.href = "/list";
//                        }
//                    },
//                    error : function () {
//                        alert("服务器异常");
//                    },
//                    beforeSend : function () {
//                        $("#btn").append($("<i class='fa fa-spinner fa-spin'></i>")).attr("disabled","disabled");
//                    },
//                    complete : function () {
//                        $("#btn").html("登录").removeAttr("disabled");
//                    }
//                });
//            }
//
//        });
    });
</script>
</body>
</html>
