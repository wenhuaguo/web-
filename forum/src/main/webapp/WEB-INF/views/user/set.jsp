<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/18
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/js/uploader/webuploader.css">
</head>
<body>
<%@ include file="../include/navbar.jsp"%>
<!--header-bar end-->
<div class="container">
    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-cog"></i> 基本设置</span>
        </div>

        <form action="" class="form-horizontal" id="emailForm">
            <div class="control-group">
                <label class="control-label" >账号</label>
                <div class="controls">
                    <input type="text" name="username" readonly value="${sessionScope.curr_user.username}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">电子邮件</label>
                <div class="controls">
                    <input type="text" name="email" value="${sessionScope.curr_user.email}">
                </div>
            </div>
            <div class="form-actions">
                <button class="btn btn-primary" type="button" id="changeEmail">保存</button>
            </div>

        </form>

    </div>
    <!--box end-->
    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-key"></i> 密码设置</span>
            <span class="pull-right muted" style="font-size: 12px">如果你不打算更改密码，请留空以下区域</span>
        </div>

        <form action="" class="form-horizontal" id="resetpassword">
            <div class="control-group">
                <label class="control-label">原始密码</label>
                <div class="controls">
                    <input type="password" name="oldpassword">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">密码</label>
                <div class="controls">
                    <input type="password" name="newpassword" id="newpassword">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">重复密码</label>
                <div class="controls">
                    <input type="password" name="repassword">
                </div>
            </div>
            <div class="form-actions">
                <button class="btn btn-primary" type="button" id="resetBtn">保存</button>
            </div>

        </form>

    </div>
    <!--box end-->

    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-user"></i> 头像设置</span>
        </div>

        <form action="" class="form-horizontal">
            <div class="control-group">
                <label class="control-label">当前头像</label>
                <div class="controls">
                    <img id="avatar" src="http://ohwsqq8z2.bkt.clouddn.com/${sessionScope.curr_user.avatar}?imageView2/1/w/20/h/20" class="img-circle" alt="">
                </div>
            </div>
            <hr>
            <p style="padding-left: 20px">关于头像的规则</p>
            <ul>
                <li>禁止使用任何低俗或者敏感图片作为头像</li>
                <li>如果你是男的，请不要用女人的照片作为头像，这样可能会对其他会员产生误导</li>
            </ul>
            <div class="form-actions">
                <div id="picker">上传新头像</div>
            </div>


        </form>

    </div>
    <!--box end-->

</div>
<!--container end-->
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/uploader/webuploader.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/js/user/set.js"></script>
<script src="/static/js/layer/layer.js"></script>
<script>
    $(function () {
        //文件上传
        //1.初始化webuploader将对象作为参数
        var uploader = WebUploader.create({
            //1.swf文件的路径
            swf:'/static/js/uploader/Uploader.swf',
            //2.文件接收服务器为七牛服务器地址
            server:"http://up-z1.qiniu.com/",
            //3.选择文件的按钮（可选）
            pick:'#picker',
            //4.是否为自动上传（默认为否）
            auto:true,
            formData:{
                'token':'${token}'
            },
            fileVal:'file',
            //5.对客户端上传的文件进行限制
            accept:{
                //title文字描述
                title: 'Images',
                //extensions:允许的文件后缀不带点多个用,逗号分隔
                extensions: 'gif,jpg,jpeg,bmp,png',
                //允许的mimetype类型全部用下面表示
                mimeTypes: 'image/*'
            }
        });
        //需要几个事件
        uploader.on("uploadSuccess",function (file,data) {
            var key = data.key;
            $.post("/set?action=avatar",{'key':key}).done(function (data) {
                if(data.state == 'success'){
                    var url = "http://ohwsqq8z2.bkt.clouddn.com/" + key;
                    $("#avatar").attr("src",url+"?imageView2/1/w/40/h/40");
                    $("#navabar_avatar").attr("src",url+"?imageView2/1/w/20/h/20");
                }
            }).error(function () {
                alert("头像设置失败");
            })
        });
        uploader.on("uploadError",function (file) {
            alert("服务器异常请稍后再试");
        })
    });
</script>
</body>
</html>