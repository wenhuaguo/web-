
<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2017/1/21
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>系统登录</title>
    <%@include file="include/css.jsp"%>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
</head>
<body class="hold-transition login-page" style="background-image: url(/static/img/bg.jpg);">
<div class="login-box">
    <div class="login-logo">
        <a href="../../index2.html" style="color: floralwhite"><h2>功成企业管理系统登录</h2></a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body" style="background-color: #ffe;">
        <p class="login-box-msg">请输入帐号密码</p>
        <c:if test="${not empty message}">
            <div class="alert alert-info">${message}</div>
        </c:if>
        <form  method="post" >
            <div class="form-group has-feedback">
                <input type="text" name="userName" value="${userName}" class="form-control" placeholder="帐号/邮箱/手机号码">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="password" value="${password}" class="form-control" placeholder="密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label style="margin-left:20px"><input type="checkbox" name="rememberMe" value="rememberMe" id="rem">记住账号和密码</label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
                <!-- /.col -->
            </div>
        </form>


        <!-- /.social-auth-links -->

        <a href="#">忘记密码</a><br>

    </div>
    <!-- /.login-box-body -->



</div>
<!-- /.login-box -->

<%@include file="include/js.jsp"%>
<script>
    $(function () {
//     $("#rem").click(function () {
//           var $rem = $("#rem");
//         console.log($rem[0].checked);
//            if(!$rem[0].checked){
//                console.log($rem[0].checked);
//                $rem.removeAttr("checked");
//            }else {
//                $rem.attr("checked","checked");
//            }
//        });
    });
</script>
</body>
</html>
