<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/17
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<%@ include file="../include/navbar.jsp"%>
<!--header-bar end-->
<div class="container">
    <div class="box">
        <div class="box-header">
            <span class="title">找回密码</span>
        </div>

        <form  class="form-horizontal" id="foundPasswordForm">
            <div class="control-group">
                <label class="control-label">找回方式</label>
                <div class="controls">
                    <select name="type" id="type">
                        <option value="email">邮箱找回</option>
                        <option value="phone" >手机找回</option>
                    </select>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" id="typename">邮箱找回</label>
                <div class="controls">
                    <input type="text" name="value">
                </div>
            </div>
            <div class="form-actions">
                <button class="btn btn-primary" type="button" id="foundPasswordBtn">提交</button>
            </div>

        </form>


    </div>
    <!--box end-->
</div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/js/user/foundPassword.js"></script>
<!--container end-->
</body>
</html>
