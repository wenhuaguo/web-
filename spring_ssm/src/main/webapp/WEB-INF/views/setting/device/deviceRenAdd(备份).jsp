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
    <title>�豸��������</title>
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
                <li><a href="#"><i class="fa fa-dashboard"></i> ҵ��</a></li>
                <li><a href="#">�豸����</a></li>
                <li class="active">������ˮ</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">����������ˮ</h3>

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

                                    <!--�豸 -->
                                    <div class="row">

                                        <div class="col-lg-3">
                                            <div >
                                                <label>�豸���ƣ�&nbsp</label>
                                                <select name="deviceId" id="select" style="height:25px;width:160px">
                                                    <option id="option">--��ѡ���豸����--</option>
                                                    <c:forEach items="${deviceList}" var="device">
                                                        <option value="${device.id}" id="deviceName" name="deviceId">${device.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div >
                                                <label for="unit">�� &nbsp;&nbspλ��&nbsp</label>
                                                <input  type="text" class="input" id="unit" value="" name="unit" placeholder="" >
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div>
                                                <label for="daynumber">���޵���(Ԫ/��)��&nbsp</label>
                                                <input type="text" name="dayPrice" disabled="disabled" class="input" id="dayPrice" value="" placeholder="">
                                            </div>
                                        </div>

                                    </div>


                                    </br>
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">��ʼʱ�䣺&nbsp</label>
                                                <input type="text" name="startTime" id="datepickerStar" class="input">
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">�黹ʱ�䣺&nbsp</label>
                                                <input type="text" id="datepickerEnd" name="endTime" class="input">
                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div >
                                                <label for="number">�� &nbsp;&nbsp����&nbsp</label>
                                                <input type="hidden" id="stockInventory" value="">
                                                <input type="text" class="input" id="num" name="num" placeholder="" >
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div>
                                                <label for="daynumber">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp����&nbsp</label>
                                                <input type="text" name="day" disabled="disabled" class="input" id="daynumber" placeholder="���ݹ黹ʱ���Զ�����">
                                            </div>
                                        </div>
                                    </div>


                                    <!--��˾ -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="company">���޹�˾��&nbsp</label>
                                                <input type="text"  class="input" id="rentCompany" name="company" placeholder="">

                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div >
                                                <label for="representative">�� &nbsp;&nbspַ��&nbsp</label>
                                                <input type="text" class="input" id="address" name="address" placeholder="" >
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="telephone">��˾�绰��&nbsp</label>
                                                <input type="text" class="input" id="telephone" name="companyTel" placeholder="" >
                                            </div>
                                        </div>



                                    </div>


                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="legalRepresentative">���˴���&nbsp</label>
                                                <input type="text"  name="legalRepresent" class="input" id="legalRepresentative" placeholder="">

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="telephone">�� &nbsp;&nbsp����&nbsp</label>
                                                <input type="text" class="input" name="LegalTel" placeholder="" >
                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div>
                                                <label for="idNum">���֤�ţ�&nbsp</label>
                                                <input type="text" class="input" name="idNum" id="idNum" placeholder="">
                                            </div>
                                        </div>
                                    </div>


                                    <!--��� -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="sumMoney">����&nbsp</label>
                                                <input type="text" disabled="disabled" class="input" name="sumMoney" id="sumMoney" placeholder=""value="">

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="firstMoney">Ԥ���&nbsp</label>
                                                <input type="text" class="input" id="firstMoney" disabled="disabled" name="firstMoney" placeholder="" value="">
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div >
                                                <label for="lastMoney">β&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�&nbsp</label>
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
                                            <span class="title"><i class="fa fa-user"></i> ��ͬ�ϴ�</span>
                                        </div>
                                        <form action="" class="form-horizontal">
                                            <hr>
                                            <p style="padding-left: 20px">ע������</p>
                                            <ul>
                                                <li>�ϴ���ͬɨ���Ҫ�������ɼ�</li>
                                                <li>��ͬ���빫˾����ǩ�ָ���</li>
                                            </ul>
                                            <div class="form-actions">
                                                <div id="picker">�ϴ���ͬ</div>

                                            </div>
                                        </form>

                                    </div>


                                    <div class="row">

                                        <div class="col-lg-3">

                                        </div>
                                        <div class="col-lg-3">
                                            <div class="box-footer">
                                                <button type="button" id="btn" class="btn btn-primary">�ύ</button>
                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                                <button type="button" id="reset" class="btn btn-primary">����</button>
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
                    alert("����������");
                }
            });
            $("#unit").val($("#select").val());
            //alert($("#select").val());
        });
        $("#datepickerStar").datepicker({
            language: "zh-CN",
            autoclose: true,//ѡ��֮���Զ���������ѡ���
            //clearBtn: true,//�����ť
            //todayBtn: true,//���հ�ť
            format: "yyyy-mm-dd"//���ڸ�ʽ����� http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        });
        $("#datepickerEnd").datepicker({
            language: "zh-CN",
            autoclose: true,//ѡ��֮���Զ���������ѡ���
            //clearBtn: true,//�����ť
            //todayBtn: true,//���հ�ť
            format: "yyyy-mm-dd"//���ڸ�ʽ����� http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        });
        $("#datepickerStar").change(function () {
            var dateStar= $("#datepickerStar").val();
        });
        $("#datepickerEnd").change(function () {
            //��ÿ�ʼʱ��
            var star = new Date($("#datepickerStar").val());
            //��ý���ʱ��
            var end = new Date($("#datepickerEnd").val());
            if(star != '' && end != ''){
                if(star.getTime() > end.getTime()){
                    alert("����ʱ�䲻��С�ڿ�ʼʱ��");
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
                alert("��������С��1");
            }else if($curentNum != '' && $num > $curentNum){
                alert("���ܴ��ڿ������");
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
        //�ļ��ϴ�
        //��ʼ��webuploader
        var uploader = WebUploader.create({
            //swf�ļ�·��
            swf : "js/uploader/Uploader.swf",
            //�ļ����շ����Ϊ��ţ��������
            server: "http://up-z1.qiniu.com/",
            //�ļ��İ�ť
            pick: '#picker',
            //�Զ��ϴ�
            auto : true,
            formData:{
                'token':'${token}'
            },
            //�ϴ��Ŀؼ�������Ϊfile
            fileVal:'file',
            //�Կͻ����ϴ����ļ���������
            accept: {
             title: 'Images',
             extensions: 'gif,jpg,jpeg,bmp,png',
             mimeTypes: 'image/*'
             }
        });
        //�ϴ��ļ����¼�
        uploader.on("uploadSuccess",function (file,data) {
            var key = data.key;
            alert("�ϴ��ɹ�" + key);
            $("#contract").val(key);
        });
        uploader.on("uploadError",function () {
            alert("�������쳣,���Ժ�����");
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
                    required:"��ѡ���豸����"
                },
                starTime:{
                    required:'��ѡ��ʼ����'
                },
                endTime:{
                    required:"��ѡ��黹����"
                },
                company:{
                    required:'�����빫˾����'
                },
                address:{
                    required:"�����빫˾��ַ"
                },
                companyTel:{
                    required:"�����빫˾�绰",
                    rangelength:"�绰����Ϊ6λ"
                },
                legalRepresent:{
                    required:"�����뷨�˴�������"
                },
                legalTel:{
                    required:"��������˵绰",
                    rangelength:"�绰����Ϊ11λ"
                },
                idNum:{
                    required:"���������֤����",
                    rangelength:"���֤����Ϊ18λ"
                },
                num:{
                    required:"����������",
                    min:"��������С��1"
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
