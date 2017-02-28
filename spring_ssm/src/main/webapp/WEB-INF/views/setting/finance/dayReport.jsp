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
    <title>财务报表-日报</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.min.css">
    <link rel="stylesheet" href="/static/plugins/datatables/extensions/FixedHeader/css/dataTables.fixedHeader.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="business_finance_dayReport"/>
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
                <li class="active"><a href="#">日报</a></li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">财务日报</h3>
                      查询日期
                    <input type="text" id="changeDate">
                    <div class="box-tools pull-right">
                        <button type="button" id="exportCsv" class="btn btn-primary"><i class="fa fa-file-o"></i>导出Excel</button>
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
                                <thead>
                                <tr>
                                    <th>id</th>
                                    <th>财务流水号</th>
                                    <th>业务模块</th>
                                    <th>业务流水号</th>
                                    <th>金额</th>
                                    <th>状态</th>
                                    <th>创建时间</th>
                                    <th>备注</th>
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
                    <div style="font-size:18px;font-weight:100" >2016年10月1日：收入<span class="alert-success" id="in">1000.00</span>元，支出<span class="alert-error" id="outCome">2000.00</span>元</div>
                </div>
                <!-- /.box-body -->

            </div>
            <!-- /.box -->
            <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM 容器-->
            <div class="form-group row">

                <div class="col-lg-6">
                    <div id="inCome" style="padding-left: 20px;width: 600px;height:400px;"></div>
                </div>
                <div class="col-lg-6">
                    <div id="out" style="padding-left: 20px;width: 600px;height:400px;"></div>
                </div>
            </div>

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
    //需要注意将jQuery对象转换为原生对象
    $(function () {
        //首先进行赋值
        $("#changeDate").val(moment().format("YYYY-MM-DD"));
        $("#changeDate").datepicker({
            format:"yyyy-mm-dd",
            language:"zh-CN",
            autoclose:true
        }).on("changeDate",function (e) {
            console.log(e);
            var day = e.format(0,"yyyy-mm-dd");
            console.log(day);
            var backDay  = moment(day);
            console.log(backDay);
            //dataTable.ajax.reload(false,null);表示刷新当前页面
            dataTable.ajax.reload();//表示重回到第一页进行刷新
            loadpie();
        });
        //echarts异步加载数据
        //1. 基于准备好的DOM初始化echarts如果是jQuery对象的话需要把jQuery对象转换为原生对象
        var income = echarts.init($("#inCome")[0]);
        var out = echarts.init($("#out")[0]);//将jQuery对象转换为原生对象
        var option = {
            title:{

            },
            tooltip:{
                //提示框组件
                trigger: 'item',//数据项图形触发主要在散点图饼图无类目中使用
                data:[]
            },
            legend:{

                data:[]
            },
            series:[],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        };//先设置一个空option
        income.setOption(option);//使用指定的配置项和数据显示图标
        out.setOption(option);
        function loadpie() {
            //当日起改变的时候发出一个AJAX请求异步加载数据
            $.get("/finance/report/day/in/"+$("#changeDate").val()+"/pie").done(function (result) {
                if(result.status == 'success'){
                    var nameArray = [];
                    var totalIn = 0;
                    for(var i=0;i<result.data.length;i++){
                        var obj = result.data[i];
                        console.log(obj.value);
                        totalIn+=obj.value;
                        nameArray.push(obj.name);
                    }
                    $("#in").text(totalIn);
                    income.setOption({
                        title:{
                            text:"收入统计"
                        },
                        legend:{
                            data:nameArray
                        },
                        series:[{
                            type:'pie',
                            name:'金额(元)',
                            data:result.data
                        }]
                    });
                }else {
                    layer.msg(result.message);
                }
            }).error(function () {
                layer.msg("加载饼图异常");
            });

            //支出的AJAX请求
            $.get("/finance/report/day/out/"+$("#changeDate").val()+"/pie").done(function (result) {
                if(result.status == 'success'){
                    var nameArray = [];
                    var totalOut = 0;
                    for(var i=0; i < result.data.length;i++){
                        var obj = result.data[i];
                        console.log(obj.value);
                        totalOut+=obj.value;
                        nameArray.push(obj.name);
                    }
                    $("#outCome").text(totalOut);
                    out.setOption({
                        title:{
                            text:'支出统计'
                        },
                        legend:{
                            data:nameArray
                        },
                        series:[{
                            type:'pie',
                            name:'金额',
                            data:result.data
                        }]
                    });
                };

            }).error(function () {
                layer.msg("加载饼图异常");
            });
        };
        loadpie();
        var dataTable = $(".table").DataTable({
            "serverSide":true,
            "ajax":{
                "url":"/finance/report/day/load",
                "type":"get",
                "data":function (obj) {
                    obj.date = $("#changeDate").val();//这是添加额外的参数发送到服务器端datatables支持
                }
            },
            "searching":false,
            "lengthChange":false,
            "pageLength":25,
            "ordering":false,
            "order":[[0,'desc']],
            "columns":[
                {"data":"id"},
                {"data":"serialNum"},
                {"data":"module"},
                {"data":"moduleSerial"},
                {"data":"money"},
                {"data":"state"},
                {"data":"createDate"},
                {"data":"remark"},
                {"data":function (row) {
                    if(row.state == '未确认'){
                        return "<a href='javascript:;' rel='"+row.id+"' class='change'>确认</a>";
                    }else {
                        return "";
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
        //固定表格标题栏
        new $.fn.dataTable.FixedHeader(dataTable);

        //事件委托机制
        $(document).delegate(".change","click",function () {
            var id = $(this).attr("rel");
            layer.confirm("确定已收(付)该款项了吗？",function (index) {
                layer.close(index);
                $.get("/finance/report/change/state",{"id":id}).done(function (data) {
                    if(data.status == 'success'){
                        layer.msg("确认成功");
                        dataTable.ajax.reload(false,null);//刷新当前页面
                    }
                }).error(function () {
                    layer.msg("服务器繁忙，请稍后再试");
                });
            })
        });

        //导出excel表格
        $("#exportCsv").click(function () {
            var date = $("#changeDate").val();//获得要导出哪一天的数据
            window.location.href = "/finance/report/day/"+date+"/data.xls";
        });

    });


</script>
</body>
</html>

