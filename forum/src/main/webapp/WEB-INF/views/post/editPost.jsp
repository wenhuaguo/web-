<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/20
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改主题</title>
    <link href="/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/js/editer/styles/simditor.css">
    <link rel="stylesheet" href="/static/js/simdatorEmoji/css/simditor-emoji.css">
</head>
<body>
<%@ include file="../include/navbar.jsp"%>
<!--header-bar end-->
<div class="container">
    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-plus"></i>修改主题</span>
        </div>

        <form action="" style="padding: 20px" id="newPost">
            <input type="hidden" value="${post.id}" name="postid">
            <label class="control-label">修改标题</label>
            <input type="text" name="title" style="width: 100%;box-sizing: border-box;height: 30px" value="${requestScope.post.title}">
            <label class="control-label">正文</label>
            <textarea name="content" id="editor">${requestScope.post.content}</textarea>

            <select name="nodeid" id="" style="margin-top:15px;">
                <c:forEach items="${nodeList}" var="node">
                    <option ${post.node.id==node.id ? 'selected':''} name="node" value="${node.id}">${node.nodename}</option>
                </c:forEach>
            </select>

        </form>
        <div class="form-actions" style="text-align: right">
            <button class="btn btn-primary" type="button" id="postBtn">发布</button>
        </div>


    </div>
    <!--box end-->
</div>
<!--container end-->
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/editer/scripts/module.min.js"></script>
<script src="/static/js/editer/scripts/hotkeys.min.js"></script>
<script src="/static/js/editer/scripts/uploader.min.js"></script>
<script src="/static/js/editer/scripts/simditor.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/js/layer/layer.js"></script>
<script src="/static/js/simdatorEmoji/js/simditor-emoji.js"></script>
<script>
    $(function(){
        //初始化simidator
        var editor = new Simditor({
            textarea: $('#editor'),
            upload:{
                url:'http://up-z1.qiniu.com/',
                fileKey:'file',
                params:{"token":"${token}"}
            },
            toolbar:[
                'title', 'bold','italic','underline','strikethrough','fontScale','color',
                'ol','ul','blockquote','code','table','link','image','hr',
                'indent','outdent','alignment','emoji'
            ],
            emoji:{
                imagePath:'/static/js/simdatorEmoji/emoji',
                images:[ 'smile.png','smiley.png','laughing.png','blush.png','heart_eyes.png','smirk.png','flushed.png','grin.png','wink.png','kissing_closed_eyes.png']
            }
            //是否允许上传图片允许
            //optional options
        });
        $("#postBtn").click(function () {
            $("#newPost").submit();
        });
        $("#newPost").validate({
            errorElement:'span',
            errorClass:'text-error',
            rules:{
                title:{
                    required:true
                }
            },
            messages:{
                title:{required:"标题不能为空"}

            },
            submitHandler:function (form) {
                $.ajax({
                    url:"/edit",
                    type:"post",
                    data:$(form).serialize(),
                    beforeSend:function () {
                        $("#postBtn").text("发布中...").attr("disabled","disabled");
                    },
                    success:function (json) {
                        if(json.state == "success"){
                            window.location.href = "/showPost?postId=" + json.data.id;
                        }
                    },
                    error:function () {
                        layer.alert("服务器异常");
                    },
                    complete:function () {
                        $("#postBtn").text("发布主题").removeAttr("disabled");
                    }
                });
            }
        });
    });
</script>

</body>
</html>
