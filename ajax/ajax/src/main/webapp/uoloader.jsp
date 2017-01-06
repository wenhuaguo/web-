<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/9
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
    <link rel="stylesheet" href="/static/js/webuploader/webuploader.css">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div id="picker">文件上传</div>  <button id="starBtn">开始上传</button>
    <ul id="fileList"></ul>
    <script src="/static/js/jquery-1.11.3.min.js"></script>
    <script src="/static/js/webuploader/webuploader.min.js"></script>
    <script type="text/template" id="bar">
    <div class="progress">
        <div class="progress-bar" id="{{id}}" style="width:0%">
            <span class="sr-only"></span>
        </div>
    </div>
</script>
    <script>
        $(function () {
            //上传之前的初始化
            var uploader = WebUploader.create({
                swf : "/static/js/webupoader/Uploader.swf",//低版本浏览器用flash处理告诉swf文件的位置
                server : "/upload",//上传的服务端地址
                pick:"#picker",//指定选择文件的按钮器，不指定则不创建按钮默认undefine
                fileVal : "file",//设置文件上传域的name也就是type的类型
               auto :true,//auto默认为false设置为true后不需要手动调用上传有文件即可自动上传
                accept:{
                    //对上传文件的类型的限制
                title: 'Images',
                        extensions: 'gif,jpg,jpeg,bmp,png',
                    mimeTypes: 'image/*'
                }
            });
            //选择文件放入上传队列，调用一次queued：队列，排队当文件被加入上传队列时触发
            uploader.on("fileQueued",function(file){
                //当文件被加入队列后触发file.id文件上传的序列号，file.name文件上传的名字
                console.log("File.id = " + file.id);
               var html = "<li id='"+file.id+"'>"+file.name+ "</li>";
                $("#fileList").append($(html));
            });
            //文件上传进度，上传过程中调用多次，上传过程中触发，携带上传进度
            uploader.on("uploadProgress",function(file,percentage) {
                //percentage文件上传的进度，为小数位需要进行转换为整数
                console.log("percentage:" +percentage);
                var num = parseInt(percentage*100);
                var $bar = $("#" + file.id).find("#bar_" +file.id);
                console.log("#bar-" + $bar);
                if(!$bar[0]){
                    var template = $("#bar").html();
                    template = template.replace("{{id}}","bar_" +file.id);
                    $("#" +file.id).append($(template));
                }else {
                    $bar.css("width",num+"%");
                }
            });
            //文件上传成功时触发，两个参数一个是file文件的对象和服务端返回的数据
            uploader.on("uploadSuccess",function(file,data) {
                $("#"+file.id).css("color", "green");
                console.log("data.state: " + data.state);
            });
            //文件上传失败时触发该事件有两个参数file,code出错时的状态码
            uploader.on("uploadError",function(file) {
                $("#" + file.id).css("color","darkred");
            });
            //文件上传成功或失败都会触发该事件
            uploader.on("uploadComplete",function(file) {
                $("#" + file.id).find("#bar_" + file.id).parent().remove();
            });
            $("#starBtn").click(function() {
                uploader.upload();//点击开始上传文件调用开始上传流程
            });
        });
    </script>
</body>
</html>
