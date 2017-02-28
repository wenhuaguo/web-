
<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2017/1/13
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Dashboard</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/header.jsp"%>
    <%@include file="../include/sider.jsp"%>
    <div class="content-wrapper">
        <section class="content">
            <div class ="box box-solid box-primary">
                <div class ="box-header with-border">
                    <h3 class ="box-title">添加用户</h3>
                </div>
                <div class ="box-body">
                    <form method="post">
                        <div class="form-group">
                            <label>账号</label>
                            <input type="text" name="userName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>密码(默认000000)</label>
                            <input type="password" name="password" value="000000" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>手机号(用于和微信企业号捆绑)</label>
                            <input type="text" name="mobile"  class="form-control">
                        </div>
                        <div class="form-group">
                            <label>角色</label>
                            <div>
                                <c:forEach items="${roleList}" var="role">
                                    <label class="checkbox-inline">
                                        <input type="checkbox" id="inlineCheckbox1" value="${role.id}" name="roleIds" >${role.viewName}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="form-group">
                            <button class="btn btn-success">保存</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 2.3.3
        </div>
        <strong>Copyright &copy; 2014-2015 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All rights
        reserved.
    </footer>
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
</body>
</html>

