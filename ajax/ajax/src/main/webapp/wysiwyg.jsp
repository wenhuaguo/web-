<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/12
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文本编辑器</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css"><%--引入bootstrap和simditor.css--%>
    <link rel="stylesheet" href="/static/js/simditor/styles/simditor.css">
</head>
<body>
<div class="container">
    <form action="/send" method="post">
        <%--autofocus自动获取焦点--%>
        <textarea name="message" id="editor" autofocus></textarea>
        <button class="btn btn-primary">发布</button>
    </form>
</div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<%--先引入module模块js文件，hotkeys热键，uploader上传者，simditor文档编辑按顺序的四个js文件--%>
<script src="/static/js/simditor/scripts/module.min.js"></script>
<script src="/static/js/simditor/scripts/hotkeys.min.js"></script>
<script src="/static/js/simditor/scripts/uploader.min.js"></script>
<script src="/static/js/simditor/scripts/simditor.min.js"></script>
<script>
    $(function () {
//        创建Simditor的对象并给创建对象进行传参
        var edit = new Simditor({
            textarea : $("#editor"),
            placeholder : "请输入文字",
            toolbar :true,//toolbar工具条的意思为true显示工具条，false不显示工具条
            upload : {
                url : "http://up-z1.qiniu.com/",
                params : {"token": "${token}"},
                fileKey : "file"
            }


        });
    });
</script>
</body>
</html>
