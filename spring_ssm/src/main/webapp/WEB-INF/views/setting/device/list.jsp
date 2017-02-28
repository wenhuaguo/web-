
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
    <link  rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="sys_device"/>
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
                            <input type="text" id="q_device_name" value="${queryName}" class="form-control" placeholder="设备名称">
                        </div>
                        <button class="btn btn-info" id="searchBtn" type="button">搜索</button>
                    </form>
                </div>
            </div>
            <div class ="box box-solid box-primary">
                <div class ="box-header with-border">
                    <h3 class ="box-title">设备管理</h3>
                    <div class ="box-tools pull-right">
                        <a href="/setting/device/new"><i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <div class ="box-body">
                    <c:if test="${not empty message}">
                        <div class="alert alert-success">
                                ${message}
                            <button class="close" type="button" data-dismiss="alert" aria-hidden="true">×</button>
                        </div>
                    </c:if>
                    <table class="table" id="table">
                        <thead>
                        <tr>
                            <td>id</td>
                            <th>名称</th>
                            <th>单位</th>
                            <th>总数量</th>
                            <th>库存</th>
                            <th>价格(元)</th>

                            <th width="100">#</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
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

<!-- jQuery 2.2.0 -->
<script src="/static/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script>
    <%--dataTables是一款jQuery表格插件，可以给任何HTML表格添加高级的交互功能--%>
    $(function () {
        var table = $(".table").DataTable({
                    //是键值对形式的表示
                    "lengthMenu":[10,50,100],//更改页面长度的选项类型是数字，显示5,10,25,50，75和100条记录
                    "serverSide":true,//是否开启服务器模式
                    "ajax":{
                        "url":"/setting/device/load",
                        "type":'post',
                        "data":function (obj) {
                            //有一个参数对象
                            obj.deviceName=$("#q_device_name").val();
                        }
                    },//从一个AJAX数据源读取数据给表格内容
                    "searching":false,//不适用自带的搜索模式
                    "order":[[0,'desc']],//默认排序方式
                    "columns":[//设置列特殊的初始化属性
                        {"data":"id","name":"id"},
                        {"data":"name"},
                        {"data":"unit"},
                        {"data":"totalNum","name":"total_num"},
                        {"data":"currentNum","name":"current_num"},
                        {"data":"price","name":"price"},
                        {"data":function (obj) {
                           return "<a href='javascript:;' rel='"+obj.id+"' class='delLink' >删除</a>";
                        }}
                    ],
                    "columnDefs":[//设置列定义初始化属性
                            //与column不同的是该参数允许你指定列设置选项
                        {targets:[0],visible:false},//第一行不可见下标从0开始
                        {targets:[1,2,6],orderable:false}//orderable有秩序的，按顺序得
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
        //事件委托
        $(document).delegate(".delLink","click",function () {
            if(confirm("确定要删除么？")){
                var id = $(this).attr("rel");
                $.get("/setting/device/"+ id+"/del").done(function (data) {
                    if(data == "success"){
                        alert("删除成功");
                        //dataTables重新加载
                        table.ajax.reload();
                    }
                }).error(function () {
                    alert("服务器异常");
                });
            }
        });

        //自定义搜索
        $("#searchBtn").click(function () {
            table.draw();//dataTables发出请求
        });
    });
</script>
</body>
</html>

