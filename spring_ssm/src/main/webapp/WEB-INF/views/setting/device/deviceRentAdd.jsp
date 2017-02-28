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
    <title>设备租赁合同新增</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <%@include file="../../include/css.jsp"%>


</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper" id="app">

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
                    <h3 class="box-title">新增租赁合同</h3>

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
                            <form role="form">
                                <div class="box-body" class="form-group">
                                    <!--公司 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="company">租赁公司：&nbsp</label>
                                                <input type="text"  class="input" id="rentCompany" name="company" placeholder="" tabindex="1">

                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div >
                                                <label for="representative">地 &nbsp;&nbsp址：&nbsp</label>
                                                <input type="text" class="input" id="address" name="address" placeholder="" tabindex="2">
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="telephone">公司电话：&nbsp</label>
                                                <input type="text" class="input" id="telephone" name="companyTel" placeholder="" tabindex="3">
                                            </div>
                                        </div>



                                    </div>

                                    <%--开始时间--%>
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">开始时间：&nbsp</label>
                                                <input type="text" name="startTime" id="datepickerStar" class="input" disabled="disabled">
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">归还时间：&nbsp</label>
                                                <input type="text" id="datepickerEnd" name="endTime" class="input" tabindex="4">
                                            </div>
                                        </div>




                                        <div class="col-lg-3">
                                            <div>
                                                <label for="daynumber">天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp数：&nbsp</label>
                                                <input type="text" name="day" disabled="disabled" class="input" id="daynumber" placeholder="根据归还时间自动生成">
                                            </div>
                                        </div>
                                    </div>
                                    <%--法人代表--%>
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="legalRepresentative">法人代表：&nbsp</label>
                                                <input type="text"  name="legalRepresent" class="input" id="legalRepresentative" placeholder="" tabindex="5">

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="telephone">电 &nbsp;&nbsp话：&nbsp</label>
                                                <input type="text" class="input" name="LegalTel" id="legalTel" placeholder="" tabindex="6">
                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div>
                                                <label for="idNum">身份证号：&nbsp</label>
                                                <input type="text" class="input" name="idNum" id="idNum" placeholder="" tabindex="7">
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="telephone">传 &nbsp;&nbsp真：&nbsp</label>
                                                <input type="text" class="input" id="fax" name="fax" placeholder="" tabindex="8">
                                            </div>
                                        </div>
                                    </div>
                                    <div> <br/></div>

                                    <div class="box">
                                        <div class="box-header">
                                            <h3 class="box-title">设备列表</h3>
                                            <%--增加一个遮蔽层--%>
                                            <div class="box-tools pull-right">
                                                <button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i></button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <table class="table">
                                                <thead>
                                                <tr>
                                                    <th>设备名称</th>
                                                    <th>单位</th>
                                                    <th>租赁单价</th>
                                                    <th>数量</th>
                                                    <th>总价</th>
                                                    <th>#</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr v-if="deviceArray.length == 0">
                                                    <td>暂无数据</td>
                                                </tr>
                                                <%--Vue的循环函数device是循环变量--%>
                                                <tr v-for="device in deviceArray">
                                                    <td>{{device.name}}</td>
                                                    <td>{{device.unit}}</td>
                                                    <td>{{device.price}}</td>
                                                    <td>{{device.num}}</td>
                                                    <td>{{device.total}}</td>
                                                    <%--Vue的事件@click--%>
                                                    <td><a href="javascript:;" @click="remove(device)"><i class="fa fa-trash text-danger"></i></a></td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="box-footer" style="text-align: right">
                                            总租赁费 {{total}} 元 预付款 {{preCost}} 元 尾款 {{lastCost}} 元
                                        </div>
                                    </div>

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
                                            <ul id="fileName">

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
                                                <button type="button" id="btn" class="btn btn-primary" @click="saveRent">提交</button>
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
    <div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">选择设备</h4>
                </div>
                <div class="modal-body">
                    <form action="">
                        <div class="form-group">
                            <input type="hidden" id="deviceName">
                            <label>设备名称</label>
                            <select id="select" name="deviceId" style="width: 300px;" class="form-control">
                                <option value="">--请选择设备名称--</option>
                                <c:forEach items="${deviceList}" var="device">
                                    <option value="${device.id}">${device.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>库存数量</label>
                            <input type="text" class="form-control" id="currNum" readonly>
                        </div>
                        <div class="form-group">
                            <label>单位</label>
                            <input type="text" class="form-control" id="unit" readonly>
                        </div>
                        <div class="form-group">
                            <label>租赁单价</label>
                            <input type="text" class="form-control" id="rentPrice" readonly>
                        </div>
                        <div class="form-group">
                            <label>租赁数量</label>
                            <input type="text" class="form-control" id="rentNum">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" v-on:click="addDevice">加入列表</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!-- /.content-wrapper -->

    <%@include file="../../include/footer.jsp"%>


</div>
<!-- ./wrapper -->
<%@include file="../../include/js.jsp"%>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/js/moment.js"></script>
<script src="/static/js/vue.js"></script>
<script src="/static/plugins/select2/select2.full.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    <!-- $(function() {		$( "#datepicker").datepicker();	});
    -->
        var fileArray = [];
        $(function () {
            //select2是轻量级插件目前不清楚具体功能
            $("#select").select2();
            $("#select").change(function () {
                       var id = $(this).val();
                       if(id){
                           $.get("/business/device/ajax",{'id':id}).done(function (resp) {
                               if(resp.status == 'success'){
                                   var device = resp.data;
                                   $("#deviceName").val(device.name);
                                   $("#currNum").val(device.currentNum);
                                   $("#unit").val(device.unit);
                                   $("#rentPrice").val(device.price);
                               }else {
                                   alert(resp.message);
                               }
                           }).error(function () {
                               alert("服务器异常，请稍后再试");
                           });
                       }
                   }
            );
        //开始时间默认是今天
            //日期格式化moment().format();
            $("#datepickerStar").val(moment().format("YYYY-MM-DD"));
            //归还日期
            $("#datepickerEnd").datepicker({
                //日期格式
                format:"yyyy-mm-dd",
                //语言中文
                language:"zh-CN",
                //选完是否自动关闭是
                autoclose: true,
                //当前日期加一天最少一天，格式是年月日，datepicker这个日历从哪儿开始
                startDate:moment().add(1,'days').format("YYYY-MM-DD")
            }).on("changeDate",function (e) {
                //datepicker的事件改变日期
                //e表示包含额外的属性
                console.log(e);
                //获得出租日期也就是当前时间
                var rentDay = moment();
                console.log(rentDay);
                var backDay = moment(e.format(0,'yyyy-mm-dd'));
                //moment计算两个日期之差函数可以按照年月日时分秒返回
                var days = backDay.diff(rentDay,'days')+1;
                $("#daynumber").val(days);
            });

            //文件上传
            //初始化webuploader
            var uploader = WebUploader.create({
                //swf文件路径
                swf : "js/uploader/Uploader.swf",
                //文件接收服务端为七牛服务器端
                //server: "http://up-z1.qiniu.com/",
                server:"/file/upload",
                //文件的按钮
                pick: '#picker',
                //自动上传
                auto : true,

                //上传的控件的名称为file
                fileVal:'file'
            });
            //上传的几个事件
            uploader.on("uploadSuccess",function (file,resp) {
                console.log(file);
                console.log(resp);
                layer.msg("上传成功");
                var html = "<li>"+resp.data.sourceFileName+"</li>";
                $("#fileName").append(html);
                var json = {
                    sourceFileName:resp.data.sourceFileName,
                    newFileName:resp.data.newFileName
                };
                fileArray.push(json);
                //$("#contract").val(key);
            });
            uploader.on("uploadError",function () {
                layer.msg("服务器繁忙，请稍后再试");
            });
            $("#btn").click(function () {
                $("#addRentForm").submit();
            });

            $("#reset").click(function () {
                $(".input").val("");
                $("#select").val($("#option").val());
            });
        });
        var app = new Vue({
            //将什么元素的数据交由Vue管理
            el:"#app",
            //数据
            data:{
                deviceArray:[]
            },
            methods:{
                //加入列表元素事件对应的函数
                addDevice:function () {
                    var id = $("#select").val();

                    //如果没有则添加新的json数据
                    var flag = false;
                    for(var i = 0; i < this.$data.deviceArray.length; i++){
                        var item = this.$data.deviceArray[i];
                        if(item.id == id){
                            this.$data.deviceArray[i].num = parseFloat(this.$data.deviceArray[i].num) + parseFloat($("#rentNum").val());
                            this.$data.deviceArray[i].total = parseFloat(this.$data.deviceArray[i].num)*parseFloat($("#rentPrice").val());
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        var json = {};
                        json.id = id;
                        json.name = $("#deviceName").val();
                        json.unit = $("#unit").val();
                        json.price = $("#rentPrice").val();
                        json.num = $("#rentNum").val();
                        json.total = parseFloat(json.price) * parseFloat(json.num);
                        this.$data.deviceArray.push(json);

                        console.log(this.$data.deviceArray);
                    }

                },
                //删除某一设备
                remove:function (device) {
                    layer.confirm("确定要删除么？",function (index) {
                        //splice()函数可以对数组元素进行删除或替换和插入
                        //执行删除操作时需要两个参数一个是要删除的元素的下标和删除的项数在这里表达的是删除下标为deivce元素的下标，删除的项数为1
                        //app.$data.deviceArray.indexOf(device)根据元素查找索引找不到返回-1
                        app.$data.deviceArray.splice(app.$data.deviceArray.indexOf(device),1);
                        //自动关闭弹窗
                        layer.close(index);
                    })
                },
                //提交（事件）处理函数
                //思考怎样将这么多的数据一下进行提交：思路是将表单数据封装成一个大json格式的数据
                saveRent:function () {
                    var json = {
                        deviceArray:app.$data.deviceArray,
                        fileArray:fileArray,
                        rentCompany:$("#rentCompany").val(),
                        address:$("#address").val(),
                        tel:$("#telephone").val(),
                        rentDay:$("#datepickerStar").val(),
                        backDay:$("#datepickerEnd").val(),
                        totalDate:$("#daynumber").val(),
                        legalRepresent:$("#legalRepresentative").val(),
                        legalTel:$("#legalTel").val(),
                        cardNum:$("#idNum").val(),
                        fax:$("#fax").val()
                    };
                    //JSON.stringfy(xx)将json数据转换为字符串数据
                    //JSON.parse(xx)将字符串数据转换为json数据
                    $.ajax({
                        url:"/business/device/addRentDevice",
                        type:"post",
                        data:JSON.stringify(json),
                        contentType:"application/json;charset=UTF-8",
                        success:function (data) {
                            layer.msg("保存成功" + data.status);
                            if(data.status == 'success'){
                                layer.confirm("保存成功", {
                                    btn: ['继续添加','打印合同'] //按钮
                                }, function(){
                                    //刷新当前页面
                                    window.history.go(0);
                                }, function(){
                                    window.location.href = "/business/device/rentDetail/" + data.data;
                                });
                            }else {
                                layer.msg(data.message);
                            }

                        },
                        error:function () {
                            layer.msg("服务器繁忙，稍后再试");
                        }
                    });
                }
            },
            //计算列表中添加设备的租赁总额
            computed:{
                total : function () {
                    var result = 0;
                    for (var i = 0; i < this.$data.deviceArray.length; i++){
                        var item = this.$data.deviceArray[i];
                        result += item.total;
                    }
                    if($("#daynumber").val() != ''){
                        result = result*parseFloat($("#daynumber").val());
                    }
                    return result;
                },
                preCost:function () {
                    return this.total*0.3;
                },
                lastCost:function () {
                    return this.total - this.preCost;
                }

            }
        });



</script>
</body>
</html>
