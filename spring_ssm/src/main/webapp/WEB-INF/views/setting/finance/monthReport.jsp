<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2017/1/31
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>财务报表-月报</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="business_finance_monthReport"/>
    </jsp:include>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>

                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 财务报表</a></li>
                <li class="active">月报</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">财务月报</h3>

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
                            <table class="table table-bordered">
                                <tr>
                                    <th>日期</th>
                                    <th>当日收入</th>
                                    <th>当日支出</th>
                                    <th>备注</th>
                                    <th>操作</th>

                                </tr>
                                <tr>
                                    <td>2016-10-1</td>
                                    <td>15000</td>
                                    <td>2000</td>
                                    <td>无</td>
                                    <td><a href="dayReport.html">详情</a></td>
                                </tr>
                                <tr>
                                    <td>2016-10-2</td>
                                    <td>15000</td>
                                    <td>2000</td>
                                    <td>无</td>
                                    <td><a href="dayReport.html">详情</a></td>
                                </tr>
                                <tr>
                                    <td>2016-10-3</td>
                                    <td>15000</td>
                                    <td>2000</td>
                                    <td>无</td>
                                    <td><a href="dayReport.html">详情</a></td>
                                </tr>
                                <tr>
                                    <td>2016-10-4</td>
                                    <td>15000</td>
                                    <td>2000</td>
                                    <td>无</td>
                                    <td><a href="dayReport.html">详情</a></td>
                                </tr>
                            </table>
                        </div>
                        <!-- /.box-body -->

                    </div>
                    <!-- /.box -->
                    <div style="font-size:18px;font-weight:100" >2016年10月：收入<span class="alert-success">100000.00</span>元，支出<span class="alert-error">50000.00</span>元，盈利<span class="alert-warning">50000.00</span>元</div>
                </div>
                <!-- /.box-body -->

            </div>
            <!-- /.box -->
            <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
            <div id="month" style="width:auto;height:800px"></div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@include file="../../include/footer.jsp"%>


</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script src="/static/js/echarts.js"></script>
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script>
    var table = $(".table").DataTable({
        "serverSide":true,
        "ajax":{
            url:"/finance/report/month/load",
            type:"get",
            data:function (obj) {
                
            }
        },
        "searching":false,

        
    });

</script>
</body>
</html>

