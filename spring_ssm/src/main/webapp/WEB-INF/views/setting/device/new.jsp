
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
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="business_deviceManager_new"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <div class ="box box-solid box-primary">
                <div class ="box-header with-border">
                    <h3 class ="box-title">添加设备</h3>
                </div>
                <div class ="box-body">
                    <form method="post">
                        <div class="form-group">
                            <label>设备名称</label>
                            <input type="text" name="name" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>单位</label>
                            <input type="text" name="unit" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>总数量</label>
                            <input type="text" name="totalNum" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input type="text" name="price" class="form-control">
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
        <strong>Copyright &copy; 2014-2015 <a href="http://almsaeedstudio.com">成功设备有限公司</a>.</strong> All rights
        reserved.
    </footer>
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
</body>
</html>

