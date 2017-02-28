
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
                    <h3 class ="box-title">修改用户</h3>
                </div>
                <div class ="box-body">
                    <form method="post">
                        <%--用隐藏标签将角色对应的id传给服务端--%>
                        <input type="hidden" value="${user.id}" name="id">
                        <div class="form-group">
                            <label>姓名</label>
                            <input type="text" name="userName" value="${user.userName}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>密码(如果不修改请留空)</label>
                            <input type="password" value="" name="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>角色</label>
                            <div>
                                <%--两个for循环一个是角色循环，一个是账户对应的原有的角色
                                并用一个标记flag标记角色原有选择的角色，其中标记用到<c:set var = ? value = ? scope = 默认page/>标签
                                --%>
                                <c:forEach items="${roleList}" var="role">
                                    <c:set scope="page" var="flag" value="false"/>
                                    <c:forEach items="${user.roleList}" var="userRole">
                                        <c:if test="${role.id == userRole.id}">
                                            <%--选择复选框属性是 checked--%>
                                            <label class="checkbox-inline">
                                                <input type="checkbox" checked id="inlineCheckbox1" value="${role.id}" name="roleIds" >${role.viewName}
                                            </label>
                                            <c:set var="flag" value="true"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${not flag}">
                                        <label class="checkbox-inline">
                                            <input type="checkbox" id="inlineCheckbox1" value="${role.id}" name="roleIds" >${role.viewName}
                                        </label>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-success">修改</button>
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

