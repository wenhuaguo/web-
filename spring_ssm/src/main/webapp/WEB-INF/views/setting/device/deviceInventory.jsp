<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2017/1/31
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>设备管理</title>
    <!-- Tell tde browser to be responsive to screen widtd -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <%@include file="../../include/css.jsp"%>

    <link rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="business_deviceManager_inventory"/>
    </jsp:include>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>

                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 业务</a></li>
                <li><a href="#">设备管理</a></li>
                <li class="active">设备库存</li>
            </ol>
        </section>

        <!-- Main content -->
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
                            <input type="text" id="q_device_name" value="${queryName}" class="form-control" placeholder="设备名称">
                        </div>
                        <button class="btn btn-info" id="searchBtn" type="button">搜索</button>
                    </form>
                </div>
            </div>
            <!-- Default box -->
            <div class="box">
                <div class="box-header witd-border">
                    <h3 class="box-title">设备列表</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <div class="box">

                        <div class="box-body">
                            <c:if test="${not empty message}">
                                <div class="alert alert-success">
                                    ${message}
                                </div>
                                <button class="close" type="button" data-dismiss="alert" aria-hidden="true">×</button>
                            </c:if>
                            <table class="table table-bordered" id="table">
                                <thead>
                                <tr>
                                    <th>设备号</th>
                                    <th>设备名称</th>
                                    <th>总数量</th>
                                    <th>库存量</th>
                                    <th>单位</th>
                                    <th>租金(/每单位/每天）</th>
                                    <th>修改时间</th>
                                    <th>创建时间</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->

                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.box-body -->

            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@include file="../../include/footer.jsp"%>


</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.0 -->
<script src="/static/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script>
    $(function () {
        var table = $(".table").DataTable({
            "lengthMenu": [ 5,10, 25, 50, 75, 100 ],
            "serverSide": true,
            "ajax":{
                "url":"/business/deviceInventory/load",
                "type":"post",
                "data":function (obj) {
                    obj.deviceName = $("#q_device_name").val();
                }
            },
            "searching":false,
            "order":[[0,'desc']],
            "columns":[
                {"data":"id","name":"id"},
                {"data":"name"},
                {"data":"totalNum","name":"total_num"},
                {"data":"currentNum","name":"current_num"},
                {"data":"unit"},
                {"data":"price","name":"price"},
                {"data":"createTime","name":"create_time"},
                {"data":"modifyTime"}
            ],
            "columnDefs":[
                {targets:[0,2,3,4,5],orderable:false}
            ],
            "language":{//定义中文
                "search":"搜索",
                "zeroRecords":"没有匹配的数据",
                "lengthMenu":"显示_MENU_条数据",
                "info":"显示从_START_到_END_ 条数据,共_TOTAL_条数据",
                "infoFiltered":"(从_MAX_条数据中过滤得来)",
                "loadingRecords":"加载中",
                "processing":"处理中",
                "paginate":{
                    "first":"首页",
                    "last":"末页",
                    "next":"下一页",
                    "previous":"上一页"
                }
            }
        });

        //自定义进行搜索过后，表格重新加载一次
        $("#searchBtn").click(function () {
            table.draw();//dataTables发出请求
        });

    });
</script>
</body>
</html>
