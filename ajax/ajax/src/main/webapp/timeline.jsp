<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/9
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>timeline时间线</title>
</head>
<body>
<div id="result">
</div>
<a id="newMessage" href="javascript:;" style="display: none"></a>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script>
    $(function () {
        var maxId = 0;
        function call() {
            //$.get(url,[data])使用一个HTTP get请求从服务器端加载数据
            $.get("/timeline",{"maxId" : maxId}).done(function(data) {
                if (data.length) {
                    for (var i = 0; i < data.length; i++) {
                        var item = data[i];
                        var html = "<h1>" + item.name + "->" +item.password+ "</h1>";
                        if (maxId == 0) {
                            $(html).appendTo($("#result"));//子.appendTo(父);将子元素作为父元素最后一个元素添加到父元素中去
                        } else {
                            $("#newMessage").show();
                            var span = data.length+"条新消息，点击查看";
                            console.log(span);
                            $("#newMessage").innerText = span;

                            $("#newMessage").click(function () {
                                $(html).prependTo($("#result"));//将子元素作为父元素的第一个元素添加到父元素中
                            });

                        }
                    }
                    maxId = data[0].id;
                }
            }).error(function () {
                alert("服务器异常");
                clearInterval(st);//当服务器发生异常的时候清除轮询效果
            }).complete(function () {
                $("#newMessage").hide();
            });
        };
        call();//当客户端进入网页的时候先进行执行查询一遍，然后再每隔指定时间进行调用
        var st = setInterval(call,5000);//延时调用可以取得轮询的效果对时效性要求不高的数据

    });
</script>
</body>
</html>
