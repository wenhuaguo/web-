<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2017/1/31
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>设备租赁查询</title>
    <!-- Tell tde browser to be responsive to screen widtd -->

    <!-- Bootstrap 3.3.6 -->
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.css">
    <link rel="stylesheet" href="/static/plugins/datatables/extensions/FixedHeader/css/dataTables.fixedHeader.min.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">


    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="business_deviceRent_out"/>
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
                <li><a href="#">设备租赁</a></li>
                <li class="active">业务流水</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header witd-border">
                    <h3 class="box-title">设备租赁流水</h3>

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
                                    <input type="text" class="form-control form-angle input-sm" id="q_serial_number" value="${querySerialNum}" placeholder="">
                                </div>
                                <div class="form-group form-marginR">
                                    <label for="exampleInputEmail2">租赁公司:</label>
                                    <input type="text" class="form-control form-angle input-sm" id="q_rent_company" value="${queryRentCompany}" placeholder="">
                                </div>
                                <div class="form-group form-marginR">
                                    <label for="exampleInputName2">状态:</label>
                                    <!-- <div class="input-group"> -->
                                    <select class="form-control form-angle input-sm" id="q_state">
                                        <option value="1">完成</option>
                                        <option value="0">未完成</option>
                                    </select>
                                    <input type="hidden" name="workFlowType" id="workFlowType">
                                    <!-- </div> -->
                                </div>
                                <div class="form-group form-marginR">
                                    <label for="exampleInputName2">起止时间:</label>
                                    <div class="input-group">
                                        <div class="input-group-addon form-angle input-sm"><i class="fa fa-calendar"></i></div>
                                        <input type="text"  value="${queryRentTime}" class="form-control form-angle form_datetime input-sm" name="createDate" id="q_star_time" >
                                    </div> -
                                    <div class="input-group">
                                        <div class="input-group-addon form-angle input-sm"><i class="fa fa-calendar"></i></div>
                                        <input type="text" value="${queryBackTime}" class="form-control form-angle form_datetime input-sm" name="createDate" id="q_end_time" >
                                    </div>
                                </div>
                                <a type="button" id="searchBtn" class="btn btn-default btn-sm">查询</a>
                            </form>
                        </div><!-- 筛选结束 -->
                        <div class="box-body">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>id</th>
                                    <th>流水号</th>
                                    <th>租赁公司</th>
                                    <th>法人代表</th>
                                    <th>电话号码</th>
                                    <th>创建时间</th>
                                    <th>状态</th>
                                    <th>归还时间</th>
                                    <th>#</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
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
<script src="/static/plugins/datatables/extensions/FixedHeader/js/dataTables.fixedHeader.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>

<script>
    $(function () {
        var table = $(".table").DataTable({
            "lengthChange": false,//每页显示的记录数不允许改变
            "serverSide": true,
            "pageLength": 25,//每页显示的记录数为25条记录
            "ajax":{
                "url":"/business/device/assembly/load",
                "type":"post",//默认是get方式
                "data":function (obj) {
                    //obj表示所有向服务端发送的键值对的形式
                    obj.serialNum = $("#q_serial_number").val();
                    obj.rentCompany = $("#q_rent_company").val();
                    obj.state = $("#q_state").val();
                    obj.rentTime = $("#q_star_time").val();
                    obj.backTime = $("#q_end_time").val();
                }
            },
            "searching":false,
            "order":[[0,'desc']],//默认排序方式根据第0列
            "ordering": false,//所有行不进行排序
            "columns":[
                {"data":"id","name":"id"},
                {"data":function (row) {
                    if(row.serialNum){
                        return "<a href='/business/device/rentDetail/"+row.serialNum+"'>" +row.serialNum+ "</a>";
                    }else {
                        return "";
                    }
                }},
                {"data":"rentCompany"},
                {"data":"legalRepresent"},
                {"data":"legalTel"},
                {"data":"rentTime"},
                {"data":"state"},
                {"data":"backTime"},
                {"data":function (row) {
                    console.log(row);
                    if(row.state == '完成'){
                        return "";
                    }else {
                        return "<a href='javascript:;' rel='"+row.id+"' class='btn btn-xs btn-default checkBtn'><i class='fa fa-check'></i>完成</a>";
                    }
                }}
            ],
            "columnDefs":[
                {targets:[0],visible: false}
            ],
            "language":{//定义中文
                "search":"搜索",
                "zeroRecords":"没有匹配的数据",
                "lengthMenu":"显示_MENU_条数据",
                "info":"显示从_START_到_END_ 条数据,共_TOTAL_条数据",
                "infoFiltered":"(从_MAX_条数据中过滤得来)",
                "infoEmpty": "显示第0至0项记录,共0项",
                "infoPostFix":"",
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
        new $.fn.dataTable.FixedHeader(table);//固定标题行随浏览内容改变
        //因为上面那些是后来产生的所有需要事件委托机制,将合同未完成变为已完成状态
        $(document).delegate(".checkBtn","click",function () {
            var id = $(this).attr("rel");//获得rel这个属性的值
            //给一个提示
            layer.confirm("你确定要更改合同状态吗？",function (index) {
                $.post("/business/device/contract/state",{"id":id}).done(function (data) {
                    if(data.status == 'success'){
                        //如果成功那么就重新加载一次表格
                        table.ajax.reload();
                    };
                }).error(function () {
                    layer.msg("服务繁忙，请稍后再试");
                });
                layer.close(index);//用于关闭弹框
            })
        })

        //自定义进行搜索过后，表格重新加载一次
        $("#searchBtn").click(function () {
            table.draw();//让dataTables重新发送请求
        });
        $("#q_star_time").datepicker({
            //日期格式
            format:"yyyy-mm-dd",
            language:"zh-CN",//语言中文
            autoclose:true,//选完是否自动关闭
        });
        $("#q_end_time").datepicker({
            //日期格式
            format:"yyyy-mm-dd",
            language:"zh-CN",//语言中文
            autoclose:true,//选完是否自动关闭
        });
    });
</script>
</body>
</html>
