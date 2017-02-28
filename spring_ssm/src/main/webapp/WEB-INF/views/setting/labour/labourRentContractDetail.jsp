<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2017/2/18
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title class="hidden-print">劳务外包流水详情</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="/static/plugins/select2/select2.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <%@include file="../../include/sider.jsp"%>

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
                <li class="active">流水详情</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <h3 style="text-align: center" class="visible-print-block">凯盛软件租赁合同清单</h3>
            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">设备租赁流水详情</h3>
                    <%--不打印时显示打印时不显示--%>
                    <div class="box-tools pull-right hidden-print">
                        <button type="button" id="print" class="btn btn-default btn-sm">
                            <i class="fa fa-print"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <div class="box">

                        <div class="box-body">
                            <form role="form" >
                                <div class="box-body" class="form-group">

                                    <!--公司 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="company">租赁公司：&nbsp</label>
                                                <span>${companyContract.rentCompany}</span>

                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div >
                                                <label for="representative">地 &nbsp;&nbsp址：&nbsp</label>
                                                <span>${companyContract.address}</span>
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="telephone">公司电话：&nbsp</label>
                                                <span>${companyContract.companyTel}</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="rentTime">租赁日期：&nbsp</label>
                                                <span>${companyContract.rentTime}</span>
                                            </div>
                                        </div>


                                    </div>


                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="company">法人代表：&nbsp</label>
                                                <span>${companyContract.legalRepresent}</span>


                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="telephone">电 &nbsp;&nbsp话：&nbsp</label>
                                                <span>${companyContract.legalTel}</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div>
                                                <label for="idNum">身份证号：&nbsp</label>
                                                <span>${companyContract.cardNum}</span>
                                            </div>
                                        </div>
                                    </div>

                                    <!--金额 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="backTime">归还日期：&nbsp</label>
                                                <span>${companyContract.backTime}</span>

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="sumMoney">设备租赁总金额：&nbsp</label>
                                                <span>${companyContract.totalPrice}</span>

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="firstMoney">预付款：&nbsp</label>
                                                <span>${companyContract.preCost}</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div >
                                                <label for="lastMoney">尾&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;款：&nbsp</label>
                                                <span>${companyContract.lastCost}</span>
                                            </div>
                                        </div>
                                    </div>


                                    <!--工种 -->
                                    <div class="row">
                                        <div class="col-lg-9">
                                            <div class="" style="border:1px solid #bfbfbf;">
                                                <table class="table table-bordered" >
                                                    <tr>
                                                        <th>雇佣工种</th>
                                                        <th>租赁单价（元/天）</th>
                                                        <th>数量</th>
                                                        <th>总价</th>
                                                    </tr>
                                                    <c:forEach items="${labourDetailList}" var="labour">
                                                        <tr>
                                                            <td>${labour.workType}</td>
                                                            <td>${labour.unitMoney}</td>
                                                            <td><span>${labour.rentNum}</span></td>
                                                            <td>${labour.total}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </table>

                                            </div>
                                        </div>
                                    </div>
                                    <div> <br/></div>

                                    <div class="row hidden-print">
                                        <div class="col-lg-9">
                                            <div style="border:1px solid #bfbfbf;">
                                                <table class="table table-bordered" >
                                                    <thead>
                                                    <tr>
                                                        <th>合同扫描件</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${labourFileUpload}" var="labourFile">
                                                        <tr><td><a href="/labour/download/file?id=${labourFile.id}">${labourFile.sourceFileName}</a></td></tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </form>
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
<script>
    <!-- $(function() {		$( "#datepicker").datepicker();	});
    -->
    $(function () {
        $("#print").click(function () {
            window.print();
        });
        $("#datepicker").datepicker({
            language: "zh-CN",
            autoclose: true,//选中之后自动隐藏日期选择框
            //clearBtn: true,//清除按钮
            //todayBtn: true,//今日按钮
            format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        });
        //文件上传
        var uploder = WebUploader.create({
            swf : "js/uploader/Uploader.swf",
            server: "#",
            pick: '#picker',
            auto : true,
            fileVal:'file',
            /*accept: {
             title: 'Images',
             extensions: 'gif,jpg,jpeg,bmp,png',
             mimeTypes: 'image/!*'
             }*/
        });

    });

</script>
</body>
</html>

