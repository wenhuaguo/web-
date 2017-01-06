<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/8
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSONP可以直接去请求远程服务器解决跨域问题的另外一种解决方案不过需要服务器的支持</title>
</head>
<body>
<button id="btn">load jsonp</button>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script>
    //通过ajax直接跨域向其它服务器发出请求
    $(function () {
        $("#btn").click(function () {
            //.done（）方法是请求并且状态码为200完成后服务器给返回的数据
            $.getJSON("/jsonp?method=?").done(function (data) {
                alert(data.length +"->" + data[0].id);
            }).error(function () {
                alert("服务器异常");
            });
        });
    })
</script>
</body>
</html>
