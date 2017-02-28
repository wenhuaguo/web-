<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2017/1/31
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>劳务外包流水查询</title>
    <!-- Tell tde browser to be responsive to screen widtd -->


    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.min.css">
    <link rel="stylesheet" href="/static/plugins/datatables/extensions/FixedHeader/css/dataTables.fixedHeader.min.css">
    <style>
        th {
            text-align: center;
            width: 60px;
        }
        td{
            text-align: center;
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="business_labour_business"/>
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
                <li><a href="#">劳务外包</a></li>
                <li class="active">业务流水</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header witd-border">
                    <h3 class="box-title">劳务外包流水</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <div class="box">
                        <div id="filtrate-box" class="screen-condition scd01"><!-- 筛选开始 -->
                            <form action="" class="form-inline">
                                <div class="form-group form-marginR">
                                    <label for="exampleInputName2">流水号:</label>
                                    <input type="text" class="form-control form-angle input-sm" id="exampleInputName2" placeholder="">
                                </div>
                                <div class="form-group form-marginR">
                                    <label for="exampleInputEmail2">用人单位:</label>
                                    <input type="text" class="form-control form-angle input-sm" id="exampleInputName2" placeholder="">
                                </div>
                                <div class="form-group form-marginR">
                                    <label for="exampleInputName2">状态:</label>
                                    <!-- <div class="input-group"> -->
                                    <select class="form-control form-angle input-sm" id="select_Type">
                                        <option value="1">完成</option>
                                        <option value="2">未完成</option>
                                    </select>
                                    <input type="hidden" name="workFlowType" id="workFlowType">
                                    <!-- </div> -->
                                </div>
                                <div class="form-group form-marginR">
                                    <label for="exampleInputName2">起止时间:</label>
                                    <div class="input-group">
                                        <div class="input-group-addon form-angle input-sm"><i class="fa fa-calendar"></i></div>
                                        <input type="text" class="form-control form-angle form_datetime input-sm" name="createDate" id="exampleInputName2" >
                                    </div> -
                                    <div class="input-group">
                                        <div class="input-group-addon form-angle input-sm"><i class="fa fa-calendar"></i></div>
                                        <input type="text" class="form-control form-angle form_datetime input-sm" name="createDate" id="exampleInputName2" >
                                    </div>
                                </div>
                                <a type="submit" class="btn btn-default btn-sm">查询</a>
                            </form>
                        </div><!-- 筛选结束 -->
                        <div class="box-body">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>id</th>
                                        <th >流水号</th>
                                        <th >需求公司</th>
                                        <th>公司地址</th>
                                        <th>公司电话</th>
                                        <th>法人代表</th>
                                        <th>电话号码</th>
                                        <th>身份证号</th>
                                        <th>创建时间</th>
                                        <th>状态</th>
                                        <th>总佣金</th>
                                        <th>合同</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
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

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/js/moment.js"></script>
<script src="/static/plugins/datatables/extensions/FixedHeader/js/dataTables.fixedHeader.min.js"></script>

<script>
    $(function(){
        var table = $(".table").DataTable({
            "serverSide":true,
            "searching":false,
            "order":[[0,'desc']],//默认排序方式是按第0列进行升序排列
            "ordering":false,//所有列不参与排序
            "lengthChange":false,//每页显示的记录数不允许改变
            "pageLength":25,
            "ajax":{
                "url":"/labour/list/load",
                "type":"get"
            },
            "columns":[//设置列的初始化属性
                {"data":"id"},
                {"data":"serialNum"},
                {"data":"rentCompany"},
                {"data":"address"},
                {"data":"companyTel"},
                {"data":"legalRepresent"},
                {"data":"legalTel"},
                {"data":"cardNum"},
                {"data":"rentTime"},
                {"data":"state"},
                {"data":"totalPrice"},
                {"data":function (row) {
                    return "<a href='/labour/download/zip?id="+row.id+"'>下载</a>";
                }},
                {"data":function (row) {
                    if(row.state == '完成'){
                        return "<a href='/labour/contract/detail/"+row.serialNum+"'>详情</a>";
                    }else {
                        return "<a href='/labour/contract/detail/"+row.serialNum+"'>详情</a></br><a href='javascript:;' rel='"+row.id+"' class='finish'>完成</a>";
                    }

                }}
            ],
            "columnDefs":[//设置列定义初始化属性
                //与column不同的是该参数允许你指定列设置选项
                {targets:[0],visible:false},//第一行不可见下标从0开始
            ],
            "language":{
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
        new $.fn.dataTable.FixedHeader(table);
        //状态的修改因为这些是后来修改的所有需要事件委托机制
        $(Document).delegate(".finish","click",function () {
            var id = $(this).attr("rel");
            layer.confirm("你确定要将该合同状态修改为已完成吗？",function (index) {
                $.get("/labour/change/state",{"id":id}).done(function (data) {
                    if(data.status == 'success'){
                        layer.msg("该合同状态已修改完成");
                        //window.history.go(0);//刷新当前页面
                        table.ajax.reload();//重新加载表内容
                    }
                }).error(function () {
                    layer.msg("服务器异常请稍后再试");
                });
                layer.close(index);//关闭弹窗
            });
        });
    });
</script>
</body>
</html>

