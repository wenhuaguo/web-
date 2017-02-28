
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
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="sys_accounts"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <i class="fa fa-search"></i>
                    <h3 class="box-title">搜索</h3>
                </div>
                <div class="box-body">
                    <%--搜索用get方式表单的默认方式是get提交--%>
                    <form class="form-inline">
                        <div class="form-group">
                                <input type="text" name="q_name" value="${queryName}" class="form-control" placeholder="姓名">
                        </div>
                        <div class="form-group">
                            <select name="q_role" class="form-control">
                                <option value="">--角色--</option>
                                <c:forEach items="${roleList}" var="role">
                                    <option value="${role.id}" ${role.id == queryRole ? 'selected':''}>${role.viewName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button class="btn btn-info">搜索</button>
                    </form>
                </div>
            </div>
            <div class ="box box-solid box-primary">
                <div class ="box-header with-border">
                    <h3 class ="box-title">用户管理</h3>
                    <div class ="box-tools pull-right">
                        <a href="/user/add"><i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <div class ="box-body">
                    <c:if test="${not empty message}">
                        <div class="alert alert-success">
                            ${message}
                            <button class="close" type="button" data-dismiss="alert" aria-hidden="true">×</button>
                        </div>
                    </c:if>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>姓名</th>
                            <th>角色</th>
                            <th width="100">#</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.items}" var="user">
                            <tr>
                                <td>${user.userName}</td>
                                <td>${user.roleNames}</td>
                                <td>
                                    <a href="/user/${user.id}/edit">编辑</a>
                                    <a href="/user/${user.id}/del">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="box-footer">
                        <nav>
                            <ul id="page" style="margin:5px 0px" class="pagination pull-right"></ul>
                        </nav>

                </div>
            </div>

        </section>
    </div>

    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 2.3.3
        </div>
        <strong>Copyright &copy; 2014-2015 <a href="http://almsaeedstudio.com">凯盛软件</a>.</strong> All rights
        reserved.
    </footer>
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/jquery.twbsPagination.min.js"></script>
<script>
    <%--文档就绪函数--%>
    $(function () {
            $("#page").twbsPagination({
                totalPages:${page.totalPage},
                visiblePages:5,
                href:"/user?q_name=&q_role&p={{number}}",
                first:"首页",
                prev:"上一页",
                next:"下一页",
                last:"末页"
            });
    });
</script>
</body>
</html>

