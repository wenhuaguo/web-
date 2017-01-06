<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/12
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/js/webuploader/webuploader.css">
</head>
<body>
<div id="picker">文件上传</div>
<div id="result"></div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/webuploader/webuploader.min.js"></script>
<script>

    $(function (){
        //初始化web uploader
        var uploader = WebUploader.create({
            //swf文件的路径
            swf : "/static/js/webuploader/Uploader.swf",
            server : "http://up-z1.qiniu.com/",//文件接收服务器地址
            pick : "#picker",
            auto : true, //选中文件后是否自动上传文件true表示自动，默认是false
            fileVal : "file",//文件name的值
            formData :{
                "token":"${token}",
                "x:uid":"${id}"//魔法变量是系统提供的预定义变量，以x:开头
            }//文件上传请求的参数列表，每次发送都会发送次对象中的参数
        });
        uploader.on("uploadSuccess",function(file,data) {
            console.log(data);
            var img = $("#result").find("img");//从父元素的中找子元素
            console.log(img);
            if(img[0]){
                img.remove();//如果存在img图片，移除img元素将元素本身删除
            }
            //key获得文件保存在空间中的资源名
            var url = "http://ohwsqq8z2.bkt.clouddn.com/" + data.key + "?imageView2/1/w/150/h/150";
            $("<img>").attr("src",url).addClass("img-circle").appendTo($("#result"));
            alert(data["x:uid"]);//魔法变量带有特殊符号获得值需要加[]表示
        });
        uploader.on("uploadError",function(file) {
            alert("服务器异常");
        })
    });
</script>
</body>
</html>
