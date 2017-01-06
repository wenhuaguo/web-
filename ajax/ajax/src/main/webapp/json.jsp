<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/6
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="text" id="name">
<span id="loading" style="display: none"><img src="/static/img/loding.gif"></span>
<span id="text"></span>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script>
    <!--文档就绪函数-->
    $(function () {
        $("#name").blur(function () {
            var name = $(this).val();
            $.ajax({
                url : "/ajax",
                type: "post",
                data:{"name":name,"age":23},
                timeout:150000,
                beforeSend: function () {
                    //请求发送之前做的函数
                    $("#loading").show();
                    $("#text").text("");
                },
                success: function (data) {
                   $("#text").text(data)
                },
                error:function () {
                    alert("服务器异常")
                },
                complete:function () {
                    //无论success还是error都会执行的函数
                    $("#loading").hide();
                }

            })
        });
    });
</script>
</body>
</html>
