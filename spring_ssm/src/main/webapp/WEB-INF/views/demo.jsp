<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2017/1/13
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Dashboard</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
   <%@include file="include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

<%@include file="include/header.jsp"%>
    <%@include file="include/sider.jsp"%>
    <div class="content-wrapper">
       <section class="content">
           <div class ="box box-solid box-info">
               <div class ="box-header with-border">
                   <h3 class ="box-title">默认框示例</h3>
                   <div class ="box-tools pull-right">
                       <！ - 按钮，标签，和许多其他的东西可以放在这里！ - >
                       <！ - 这里是一个标签，例如 - >
                       <span class ="label label-primary">标签</span>
                   </div> <！ -  /.box-tools  - >
               </div> <！ -  /.box-header  - >
               <div class ="box-body">
                   盒子的主体
               </div> <！ -  /.box-body  - >
               <div class ="box-footer">
                   盒子的页脚
               </div> <！ -  box-footer  - >
           </div> <！ -  /.box  - >
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

<%@include file="include/js.jsp"%>
</body>
</html>

