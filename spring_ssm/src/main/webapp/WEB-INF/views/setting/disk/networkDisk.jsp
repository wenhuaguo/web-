<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2017/1/31
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>公司网盘</title>
    <!-- Tell tde browser to be responsive to screen widtd -->
    <meta content="widtd=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
   <%@include file="../../include/css.jsp"%>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="networkDisk"/>
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
                <li><a href="#"><i class="fa fa-dashboard"></i> 公司网盘</a></li>
                <li><a href="#">浏览网盘</a></li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header witd-border">
                    <h3 class="box-title">文件列表</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <div class="box">
                        <div class="form-actions" style="float:right">
                            <button class="btn btn-primary newFile"  style="float:right" id="newFile">新建文件夹</button>&nbsp;&nbsp;
                            <div id="picker" style="float:right">上传文件</div>&nbsp;&nbsp;

                        </div>
                        <div class="box-body">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th style="text-align: center">文件类型</th>
                                        <th style="text-align: center">文件名称</th>
                                        <th style="text-align: center">大小</th>
                                        <th style="text-align: center">创建时间</th>
                                        <th style="text-align: center">创建人</th>
                                        <th style="text-align: center">#</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:if test="${empty diskList}">
                                    <td>暂无数据</td>
                                </c:if>
                                <c:forEach items="${diskList}" var="disk">
                                    <tr class="fileName">
                                        <td>
                                            <c:choose>
                                                <c:when test="${disk.docType == 'file'}">
                                                    <i class="fa fa-file-o"></i>
                                                </c:when>
                                                <c:otherwise>
                                                    <i class="fa fa-folder-o"></i>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                            <c:when test="${disk.docType == 'directory'}">
                                        <a href="/pan/list?path=${disk.id}"><span>${disk.sourceName}</span></a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="/pan/downLoad?file=${disk.id}"><span>${disk.sourceName}</span></a>
                                        </c:otherwise>
                                        </c:choose>
                                        </td>
                                        <td>${disk.size}</td>
                                        <td>${disk.createTime}</td>
                                        <td>${disk.createUser}</td>
                                        <td><a href="javascript:;" rel="${disk.id}" class="del" fileType="${disk.docType}"><i class="fa fa-trash text-danger"></i></a></td>
                                    </tr>
                                </c:forEach>
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
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        //新建文件夹
        $("#newFile").click(function () {
            layer.prompt({title: '请输入文件夹名称'}, function(pass, index){
                //关闭弹出层
                layer.close(index);
                var fid = ${fid};
                //pass是获得输入的名称
                $.post("/pan/folder/new",{"sourceName":pass,"fid":fid}).done(function (data) {
                    if(data.status == 'success'){
                    //刷新当前页面
                        window.history.go(0);
                    }
                }).error(function () {
                    layer.msg("服务器异常，请稍后再试")
                });

            });
        });

        //文件上传
        var uploder = WebUploader.create({
            swf : "/static/js/uploader/Uploader.swf",
            server: "/pan/file/upload",
            pick: '#picker',
            auto : true,
            fileVal:'file',
            formData:{"fid":${fid}}
        });

        //文件上传的几个事件
        uploder.on("uploadSuccess",function (file,resp) {
            console.log("file"+ file+"resp"+resp);
            if(resp.status == 'success'){
                layer.msg("文件上传成功");
                window.history.go(0);//提醒上传成功后，刷新当前页面
            }else {
                layer.msg(resp.message);
            }
        });
        uploder.on("uploadError",function () {
            layer.msg("服务异常，请稍后再试");
        });

        //文件和文件夹的删除必须用类选择器不能用id选择器如果用id选择器的话只会标识一个不能标识全部
        $(".del").click(function () {
            var fileType = $(this).attr("fileType");
            console.log(fileType);
            //给一个提醒
            var id = $(this).attr("rel");//获得rel属性对应的值
            layer.confirm("你确定要删除该" + (fileType == 'file' ? '文件':'文件夹')+"吗?",function (index) {
                layer.close(index);//用于关闭弹窗
                $.get("/pan/del",{"id":id}).done(function (data) {
                    if(data.status == 'success'){
                        layer.msg("删除成功");
                        window.history.go(0);
                    }else {
                        layer.msg(data.message);
                    }
                }).error(function () {
                    layer.msg("服务器异常，请稍后再试");
                });
            });
        });

    });
</script>
</body>
</html>

