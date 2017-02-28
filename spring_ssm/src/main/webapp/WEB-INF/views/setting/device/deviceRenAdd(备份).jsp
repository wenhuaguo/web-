<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2017/1/31
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>设备租赁新增</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="business_deviceRent_add"/>
    </jsp:include>

    <!-- =============================================== -->

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
                <li class="active">新增流水</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增租赁流水</h3>

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
                            <form role="form" id="addRentForm">
                                <div class="box-body" class="form-group">

                                    <!--设备 -->
                                    <div class="row">

                                        <div class="col-lg-3">
                                            <div >
                                                <label>设备名称：&nbsp</label>
                                                <select name="deviceId" id="select" style="height:25px;width:160px">
                                                    <option id="option">--请选择设备名称--</option>
                                                    <c:forEach items="${deviceList}" var="device">
                                                        <option value="${device.id}" id="deviceName" name="deviceId">${device.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div >
                                                <label for="unit">单 &nbsp;&nbsp位：&nbsp</label>
                                                <input  type="text" class="input" id="unit" value="" name="unit" placeholder="" >
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div>
                                                <label for="daynumber">租赁单价(元/天)：&nbsp</label>
                                                <input type="text" name="dayPrice" disabled="disabled" class="input" id="dayPrice" value="" placeholder="">
                                            </div>
                                        </div>

                                    </div>


                                    </br>
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">开始时间：&nbsp</label>
                                                <input type="text" name="startTime" id="datepickerStar" class="input">
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">归还时间：&nbsp</label>
                                                <input type="text" id="datepickerEnd" name="endTime" class="input">
                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div >
                                                <label for="number">数 &nbsp;&nbsp量：&nbsp</label>
                                                <input type="hidden" id="stockInventory" value="">
                                                <input type="text" class="input" id="num" name="num" placeholder="" >
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div>
                                                <label for="daynumber">天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp数：&nbsp</label>
                                                <input type="text" name="day" disabled="disabled" class="input" id="daynumber" placeholder="根据归还时间自动生成">
                                            </div>
                                        </div>
                                    </div>


                                    <!--公司 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="company">租赁公司：&nbsp</label>
                                                <input type="text"  class="input" id="rentCompany" name="company" placeholder="">

                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div >
                                                <label for="representative">地 &nbsp;&nbsp址：&nbsp</label>
                                                <input type="text" class="input" id="address" name="address" placeholder="" >
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="telephone">公司电话：&nbsp</label>
                                                <input type="text" class="input" id="telephone" name="companyTel" placeholder="" >
                                            </div>
                                        </div>



                                    </div>


                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="legalRepresentative">法人代表：&nbsp</label>
                                                <input type="text"  name="legalRepresent" class="input" id="legalRepresentative" placeholder="">

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="telephone">电 &nbsp;&nbsp话：&nbsp</label>
                                                <input type="text" class="input" name="LegalTel" placeholder="" >
                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div>
                                                <label for="idNum">身份证号：&nbsp</label>
                                                <input type="text" class="input" name="idNum" id="idNum" placeholder="">
                                            </div>
                                        </div>
                                    </div>


                                    <!--金额 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="sumMoney">租金金额：&nbsp</label>
                                                <input type="text" disabled="disabled" class="input" name="sumMoney" id="sumMoney" placeholder=""value="">

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="firstMoney">预付款：&nbsp</label>
                                                <input type="text" class="input" id="firstMoney" disabled="disabled" name="firstMoney" placeholder="" value="">
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div >
                                                <label for="lastMoney">尾&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;款：&nbsp</label>
                                                <input type="text" id="lastMoney" class="input" disabled="disabled" name="lastMoney" placeholder="" value="">
                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div>
                                                <input type="hidden" id="contract" class="input" disabled="disabled" name="contract" placeholder="" value="">
                                            </div>
                                        </div>
                                    </div>
                                    <div> <br/></div>



                                    <div class="box" style="padding-left: 20px">
                                        <div class="box-header">
                                            <span class="title"><i class="fa fa-user"></i> 合同上传</span>
                                        </div>
                                        <form action="" class="form-horizontal">
                                            <hr>
                                            <p style="padding-left: 20px">注意事项</p>
                                            <ul>
                                                <li>上传合同扫描件要求清晰可见</li>
                                                <li>合同必须公司法人签字盖章</li>
                                            </ul>
                                            <div class="form-actions">
                                                <div id="picker">上传合同</div>

                                            </div>
                                        </form>

                                    </div>


                                    <div class="row">

                                        <div class="col-lg-3">

                                        </div>
                                        <div class="col-lg-3">
                                            <div class="box-footer">
                                                <button type="button" id="btn" class="btn btn-primary">提交</button>
                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                                <button type="button" id="reset" class="btn btn-primary">重置</button>
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
<script src="/static/js/jquery.validate.min.js"></script>
<script>
    <!-- $(function() {		$( "#datepicker").datepicker();	});
    -->
    $(function () {
        $("#select").change(function () {
            var $deviceId = $("#select").val();
            $.ajax({
              type:"post",
                url:"/business/device/ajax",
                data:{
                    "deviceId":$("#select").val()
                },
                data:JSON.stringify({
                    "deviceId":$("#select").val()
                }),
                dataType:'json',
                contentType:'application/json',
                success:function (obj) {
                    if(obj.device){
                        $("#unit").val(obj.device.unit);
                        $("#dayPrice").val(obj.device.price);
                        $("#stockInventory").val(obj.device.currentNum);
                    }
                },
                error:function () {
                    alert("服务器错误");
                }
            });
            $("#unit").val($("#select").val());
            //alert($("#select").val());
        });
        $("#datepickerStar").datepicker({
            language: "zh-CN",
            autoclose: true,//选中之后自动隐藏日期选择框
            //clearBtn: true,//清除按钮
            //todayBtn: true,//今日按钮
            format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        });
        $("#datepickerEnd").datepicker({
            language: "zh-CN",
            autoclose: true,//选中之后自动隐藏日期选择框
            //clearBtn: true,//清除按钮
            //todayBtn: true,//今日按钮
            format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        });
        $("#datepickerStar").change(function () {
            var dateStar= $("#datepickerStar").val();
        });
        $("#datepickerEnd").change(function () {
            //获得开始时间
            var star = new Date($("#datepickerStar").val());
            //获得结束时间
            var end = new Date($("#datepickerEnd").val());
            if(star != '' && end != ''){
                if(star.getTime() > end.getTime()){
                    alert("结束时间不能小于开始时间");
                    $("#datepickerEnd").val("");
                }else {
                    var date_diff = Math.abs(end.getTime() - star.getTime())/86400000;
                    $("#daynumber").val(date_diff);
                }
            }

        });
        $("#num").change(function () {
            var $num = $("#num").val();
            var $dayPrice = $("#dayPrice").val();
            var $dayNum = $("#daynumber").val();
            var $curentNum = $("#stockInventory").val();
            if($num <= 0){
                alert("数量不能小于1");
            }else if($curentNum != '' && $num > $curentNum){
                alert("不能大于库存数量");
                $("#num").val("");
            }else {
                if($dayNum != '' && $dayPrice != ''){
                    var sumMoney = $num*$dayPrice*$dayNum;
                    $("#sumMoney").val(sumMoney);
                    var firstMoney = sumMoney * 0.2;
                    $("#firstMoney").val(firstMoney);
                    var lastMoney = sumMoney - firstMoney;
                    $("#lastMoney").val(lastMoney);
                }
            }
        });
        //文件上传
        //初始化webuploader
        var uploader = WebUploader.create({
            //swf文件路径
            swf : "js/uploader/Uploader.swf",
            //文件接收服务端为七牛服务器端
            server: "http://up-z1.qiniu.com/",
            //文件的按钮
            pick: '#picker',
            //自动上传
            auto : true,
            formData:{
                'token':'${token}'
            },
            //上传的控件的名称为file
            fileVal:'file',
            //对客户端上传的文件进行限制
            accept: {
             title: 'Images',
             extensions: 'gif,jpg,jpeg,bmp,png',
             mimeTypes: 'image/*'
             }
        });
        //上传的几个事件
        uploader.on("uploadSuccess",function (file,data) {
            var key = data.key;
            alert("上传成功" + key);
            $("#contract").val(key);
        });
        uploader.on("uploadError",function () {
            alert("服务器异常,请稍后再试");
        });
        $("#btn").click(function () {
            $("#addRentForm").submit();
        });
        $("#addRentForm").validate({
            errorElement:'span',
            errorClass:'text-error',
            rules:{
                num:{
                    required:true
                },
                sumMoney:{
                    required:true
                },
                firstMoney:{
                    required:true
                },
                lastMoney:{
                    required:true
                },
                contract:{
                    required:true
                },
                deviceId:{
                    required:true
                },
                starTime:{
                    required:true
                },
                endTime:{
                    required:true
                },
                company:{
                    required:true
                },
                address:{
                    required:true
                },
                companyTel:{
                    required:true,
                    rangelength:[6,6]
                },
                legalRepresent:{
                    required:true
                },
                legalTel:{
                    required:true,
                    rangelength:[11,11]
                },
                idNum:{
                    required:true,
                    rangelength:[18,18]
                },
                num:{
                    required:true,
                    min:1
                }
            },
            messages:{
                num:{
                    required:""
                },
                sumMoney:{
                    required:""
                },
                firstMoney:{
                    required:""
                },
                lastMoney:{
                    required:""
                },
                contract:{
                    required:""
                },
                deviceId:{
                    required:"请选择设备名称"
                },
                starTime:{
                    required:'请选择开始日期'
                },
                endTime:{
                    required:"请选择归还日期"
                },
                company:{
                    required:'请输入公司名称'
                },
                address:{
                    required:"请输入公司地址"
                },
                companyTel:{
                    required:"请输入公司电话",
                    rangelength:"电话长度为6位"
                },
                legalRepresent:{
                    required:"请输入法人代表名字"
                },
                legalTel:{
                    required:"请输入个人电话",
                    rangelength:"电话长度为11位"
                },
                idNum:{
                    required:"请输入身份证号码",
                    rangelength:"身份证长度为18位"
                },
                num:{
                    required:"请输入数量",
                    min:"数量不能小于1"
                }
            },
            submitHandler:function (form) {
                console.log($(form).serialize());
                $.ajax({
                    url:"/business/device/addRentDevice",
                    type:"post",
                    data:$("#addRentForm").serialize(),
                    success:function (data) {
                        if(data == 'success'){
                            window.location.href = "/business/device/assembly";
                        }
                    }
                });
            }
        });
        $("#reset").click(function () {
            $(".input").val("");
            $("#select").val($("#option").val());
        });
    });

</script>
</body>
</html>
