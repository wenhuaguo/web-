<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>设备租赁新增</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/ssm/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/ssm/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/ssm/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="/static/ssm/dist/css/skins/_all-skins.min.css">

    <link rel="stylesheet" href="/static/ssm/css/bootstrap-datepicker.min.css">
    <!-- 文件上传 -->
    <link rel="stylesheet" href="/static/ssm/js/uploader/webuploader.css">
    <link rel="stylesheet" href="/static/ssm/css/style.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">


<%@include file="../../include/header.jsp"%>
    <!-- =============================================== -->

    <!-- Left side column. contains the sidebar -->
    <%@include file="../../include/sider.jsp"%>

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
                            <form role="form" >
                                <div class="box-body" class="form-group">

                                    <!--设备 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div >
                                                <label for="device_name">设备名称：&nbsp</label>
                                                <select name="" id="" style="height:25px;width:160px">
                                                    <option value="">挖掘机</option>
                                                    <option value="">塔吊</option>
                                                    <option value="">货车</option>
                                                </select>
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div >
                                                <label for="unit">单 &nbsp;&nbsp位：&nbsp</label>
                                                <input type="text" class="" name="unit" placeholder="" >
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div>
                                                <label for="daynumber">租赁单价：&nbsp</label>
                                                <input type="text" disabled="disabled" class="" id="daynumber" value="10.00">
                                            </div>
                                        </div>
                                    </div>


                                    </br>
                                    <div class="row">

                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">归还时间：&nbsp</label>
                                                <input type="text" id="datepicker" >
                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div >
                                                <label for="number">数 &nbsp;&nbsp量：&nbsp</label>
                                                <input type="text" class="" name="number" placeholder="" >
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div>
                                                <label for="daynumber">天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp数：&nbsp</label>
                                                <input type="text" disabled="disabled" class="" id="daynumber" placeholder="根据归还时间自动生成">
                                            </div>
                                        </div>
                                    </div>


                                    <!--公司 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="company">租赁公司：&nbsp</label>
                                                <input type="text"  class="" id="daynumber" placeholder="">

                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div >
                                                <label for="representative">地 &nbsp;&nbsp址：&nbsp</label>
                                                <input type="text" class="" name="representative" placeholder="" >
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="telephone">公司电话：&nbsp</label>
                                                <input type="text" class="" name="telephone" placeholder="" >
                                            </div>
                                        </div>



                                    </div>


                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="company">法人代表：&nbsp</label>
                                                <input type="text"  class="" id="daynumber" placeholder="">

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="telephone">电 &nbsp;&nbsp话：&nbsp</label>
                                                <input type="text" class="" name="telephone" placeholder="" >
                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div>
                                                <label for="idNum">身份证号：&nbsp</label>
                                                <input type="text" class="" id="idNum" placeholder="">
                                            </div>
                                        </div>
                                    </div>


                                    <!--金额 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="sumMoney">租金金额：&nbsp</label>
                                                <input type="text" disabled="disabled" class="" id="sumMoney" placeholder=""value="10000.00">

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="firstMoney">预付款：&nbsp</label>
                                                <input type="text" class="" disabled="disabled" name="firstMoney" placeholder="" value="2000.00">
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div >
                                                <label for="lastMoney">尾&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;款：&nbsp</label>
                                                <input type="text" class="" disabled="disabled" name="lastMoney" placeholder="" value="8000.00">
                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div>

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
                                                <button type="submit" class="btn btn-primary">提交</button>
                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                                <button type="submit" class="btn btn-primary">重置</button>
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

<!-- jQuery 2.2.0 -->
<script src="/static/ssm/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/ssm/bootstrap/js/bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="/static/ssm/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/ssm/dist/js/app.min.js"></script>
<!-- datepicker -->
<script src="/static/ssm/js/bootstrap-datepicker.min.js"></script>
<script src="/static/ssm/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="/static/ssm/js/uploader/webuploader.min.js"></script>
<script>
    <!-- $(function() {		$( "#datepicker").datepicker();	});
    -->
    $(function () {
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
