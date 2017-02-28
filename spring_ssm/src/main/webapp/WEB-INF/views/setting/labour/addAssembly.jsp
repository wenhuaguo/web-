<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2017/1/31
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>劳务外包新增</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper" id="labour">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="business_labour_add"/>
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
                <li class="active">新增流水</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增劳务外包流水</h3>

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

                                    <!--公司 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="rentcompany">租赁公司：&nbsp</label>
                                                <input type="text"  class="" id="rentCompany" placeholder="">

                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div >
                                                <label for="address">地 &nbsp;&nbsp址：&nbsp</label>
                                                <input type="text" class="" id="address" name="representative" placeholder="" >
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="companyTel">公司电话：&nbsp</label>
                                                <input type="text" id="companyTel" class="" name="telephone" placeholder="" >
                                            </div>
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="rentDay">用工日期：&nbsp</label>
                                                <input type="text" class="" id="rentDay" name="rentDay" placeholder="" disabled>
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="backDay">归还日期：&nbsp</label>
                                                <input type="text"  class="" id="backDay" placeholder="">

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="dayNum">用工天数：&nbsp</label>
                                                <input type="text"  class="" id="dayNum" disabled placeholder="">

                                            </div>
                                        </div>

                                    </div>

                                    <!--金额 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="legalRepresent">法人代表：&nbsp</label>
                                                <input type="text"  class="" id="legalRepresent" placeholder="">

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="legalTelephone">电 &nbsp;&nbsp话：&nbsp</label>
                                                <input type="text" class="" id="legalTel" name="legalTel" placeholder="" >
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div>
                                                <label for="idNum">身份证号：&nbsp</label>
                                                <input type="text" class="" id="idNum" placeholder="">
                                            </div>
                                        </div>
                                    </div>


                                    <!--工种 -->
                                    <div class="row">
                                        <div class="col-lg-9">
                                            <%--toggle计算机上的切换键转换键--%>

                                            <div class="" style="border:1px solid #bfbfbf;">
                                                <h5><a href="#" data-toggle="modal" data-target="#myModal">添加工种<span><i class="fa fa-plus"></i></span></a></h5>
                                                <table class="table table-bordered" >
                                                    <tr>
                                                       <th>工种类型</th>
                                                        <th>工种单位佣金</th>
                                                        <th>工种数量</th>
                                                        <th>小计</th>
                                                    </tr>
                                                    <tr>
                                                        <td v-if="labourArray.length == 0">
                                                            暂无数据
                                                        </td>
                                                    </tr>

                                                    <tr v-for="labourA in labourArray">
                                                        <td>{{labourA.workType}}</td>
                                                        <td><span>{{labourA.unitMoney}}</span></td>
                                                        <td>{{labourA.dispatchNum}}</td>
                                                        <td><span>{{labourA.total}}</span></td>
                                                        <td><a href="javascript:;" @click="remove(labourA)"><i class="fa fa-trash text-danger"></i></a></td>
                                                    </tr>

                                                </table>
                                                <div style="text-align: right">
                                                    总雇佣费{{total}}元 预付款{{preCost}}元 尾款{{lastCost}}元
                                                </div>
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
                                        <ul id="ul" style="text-align: right"></ul>
                                    </div>


                                    <div class="row">

                                        <div class="col-lg-3">

                                        </div>
                                        <div class="col-lg-3">
                                            <div class="box-footer">
                                                <button type="button" class="btn btn-primary" @click="saveLabour">提交</button>
                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                                <button type="button" class="btn btn-primary">重置</button>
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
    <div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">劳务派遣</h4>
                </div>
                <div class="modal-body">
                    <form action="">
                        <div class="form-group">
                            <input type="hidden" id="workType">
                            <label>工种</label>
                            <select id="select" name="deviceId" style="width: 300px;" class="form-control">
                                <option value="">--请选择派遣工种--</option>
                                <c:forEach items="${labourList}" var="labour">
                                    <option value="${labour.id}">${labour.workType}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>剩余人数</label>
                            <input type="text" class="form-control" id="currNum" readonly>
                        </div>
                        <div class="form-group">
                            <label>单位佣金</label>
                            <input type="text" class="form-control" id="unitMoney" readonly>
                        </div>
                        <div class="form-group">
                            <label>派遣数量</label>
                            <input type="text" class="form-control" id="dispatchNum">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" v-on:click="addLabour">加入列表</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <%@include file="../../include/footer.jsp"%>


</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script src="/static/js/vue.js"></script>
<script src="/static/js/moment.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/select2/select2.full.min.js"></script>

<script>
    //用于提交上传文件的数据
    var fileUpload = [];
    <!-- $(function() {		$( "#datepicker").datepicker();	});
    -->
    $(function () {
        $("#select").select2();
        $("#select").change(function () {
            var id = $(this).val();
            if(id){
                $.get("/labour/dispatch/labour.json",{'id':id}).done(function (data) {
                    if(data.status == 'success'){
                        $("#workType").val(data.data.workType);
                        $("#unitMoney").val(data.data.unitMoney);
                        $("#currNum").val(data.data.currentNum);
                    }
                }).error(function () {
                    layer.msg("服务器繁忙，请稍后再试");
                });
            }
        });
        //开始租赁日期从当前日期开始
        $("#rentDay").datepicker().val(moment().format("YYYY-MM-DD"));
        //当选择归还日期时内容发生改变天数框也自动生成
        $("#backDay").datepicker({
            language: "zh-CN",
            autoclose: true,//选中之后自动隐藏日期选择框
            //clearBtn: true,//清除按钮
            //todayBtn: true,//今日按钮
            format: "yyyy-mm-dd", //日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
            startDate:moment().add(1,'days').format("YYYY-MM-DD")
        }).on("changeDate",function (e) {
            //moment()日期库会计算两个日期之差
            //e表示附带额外的属性
            console.log(e);
            var rentDay = moment();
            console.log(rentDay);
            var backDay = moment(e.format(0,'yyyy-mm-dd'));
            //计算两个日期之差以什么样的形式返回可以是天、时、分、秒、月、年的形式返回
            var dayNum = backDay.diff(rentDay,'days') + 1;
            $("#dayNum").val(dayNum);
        });

        //文件上传
        var uploder = WebUploader.create({
            swf : "js/uploader/Uploader.swf",
            server: "/labour/file/upload",
            pick: '#picker',
            auto : true,
            fileVal:'file'
            /*accept: {
             title: 'Images',
             extensions: 'gif,jpg,jpeg,bmp,png',
             mimeTypes: 'image/!*'
             }*/
        });
        //上传的几个事件
        uploder.on("uploadSuccess",function (file,resp) {
            layer.msg("上传成功");
            var html = "<li>"+resp.data.sourceFile+"</li>";
            //父添子，子作为父的第一个元素
            $("#ul").append(html);
            //子添父$(html).appendto($("#ul"));
            //将上传文件成功的文件（源文件名和新文件名）封装为json数据
            var json = {
                sourceFile:resp.data.sourceFile,
                newFile:resp.data.newFile
            };
            //将封装好的json数据放入到数组当中
            fileUpload.push(json);


        });
        uploder.on("uploadError",function () {
            layer.msg("服务器繁忙，请稍后再试");
        });

    });
    //Vue的使用
    //首先创建一个Vue的对象
    var labour = new Vue({
        //将什么元素交由Vue管理
        el:"#labour",
        //数据
        data:{
            //声明一个空的数组用于装劳务添加人员
            labourArray:[]
        },
        //处理函数对象的方法都放在这里
        methods:{
            addLabour:function () {
                var id = $("#select").val();
                console.log(id);
                var flag = false;
                for(var i =0; i < this.$data.labourArray.length;i++){
                    var item = this.$data.labourArray[i];
                    if(item.id == id){
                        console.log(item.id);
                        //如果为同一元素那么将这个元素的数量增加填入的数量
                        this.$data.labourArray[i].dispatchNum = parseFloat(this.$data.labourArray[i].dispatchNum) + parseFloat($("#dispatchNum").val());
                        //根据总数量计算出工种的价格
                        this.$data.labourArray[i].total = parseFloat($("#unitMoney").val())*parseFloat(this.$data.labourArray[i].dispatchNum);
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    //将提交的数据封装为json对象
                    var json = {};
                    json.id = id;
                    json.workType = $("#workType").val();
                    json.unitMoney = $("#unitMoney").val();
                    json.dispatchNum = $("#dispatchNum").val();
                    json.total = parseFloat(json.unitMoney)*parseFloat(json.dispatchNum);
                    this.$data.labourArray.push(json);

                }
                console.log(this.$data.labourArray) ;
            },
            remove:function (labourA) {
                layer.confirm("确定要删除么？",function (index) {
                    //index参数用于关闭询问层
                    //数组删除操作splice()两个参数一个是要删除元素的下标一个是要删除的项数
                    //数组indexOf()放入元素返回下标为空则返回-1
                    labour.$data.labourArray.splice(labour.$data.labourArray.indexOf(labourA),1);
                    //自动关闭弹窗
                    layer.close(index)
                })
            },
            saveLabour:function () {
                //将整个页面的数据封装成为一个大的json数据
                var json = {
                    labourArray:this.$data.labourArray,
                    fileUpload:fileUpload,
                    //其它数据必须书写
                    rentCompany:$("#rentCompany").val(),
                    address:$("#address").val(),
                    companyTel:$("#companyTel").val(),
                    rentDay:$("#rentDay").val(),
                    backDay:$("#backDay").val(),
                    dayNum:$("#dayNum").val(),
                    legalRepresent:$("#legalRepresent").val(),
                    legalTel:$("#legalTel").val(),
                    idNum:$("#idNum").val()
                };

                $.ajax({
                    url:"/labour/ajax.json",
                    type:"post",
                    data:JSON.stringify(json),
                    contentType:"application/json;charset=UTF-8",
                    success:function (data) {
                        if(data.status == 'success'){
                            layer.confirm('保存成功',{
                                btn:['继续添加','打印合同']//按钮
                            },function () {
                                //刷新当前页面
                                window.history.go(0);
                            },function () {
                                window.location.href = "/labour/contract/detail/" + data.data;
                            })
                        }else {
                            layer.msg(data.message);
                        }
                    },
                    error:function () {
                        layer.msg("服务器异常请稍后再试")
                    }

                });
            }
        },
        computed:{
            total:function () {
                var result = 0;
                for(var i=0;i<this.$data.labourArray.length;i++){
                    result+=this.$data.labourArray[i].total;
                }
                console.log(result);
                //获得天数
                var dayNum = $("#dayNum").val();
                if(dayNum != ''){
                    result*=dayNum;
                }
                return result;
            },
            preCost:function () {
                return this.total*0.3;
            },
            lastCost:function () {
                return (this.total - this.preCost);
            }
        }
    });
</script>
</body>
</html>

