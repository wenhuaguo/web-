<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/8
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>模板</title>
</head>
<body>
<button id="btn">java script template</button>
<div id="result"></div>
<script type="text/template" id="template">
    <%--添加type属性并改为浏览器不认识的类型，这样浏览器对于将HTML元素标签放到script中才不会报错--%>
<%--Javascript解决文档模板结构的问题这样易于维护和更改--%>
    <div id="num-{{id}}">
        <h3>{{title}}</h3>
        <%--{{}}双括号占位符--%>
        <p>{{content}}</p>
    </div>
</script>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script>
     $(function () {
         $("#btn").click(function () {
             var html = $("#template").html();//获得指定选择器的html元素
             html = html.replace("{{id}}",Math.random());//替换模板中的元素或者子标签
             html = html.replace("{{title}}","hello" + new Date().getTime());
             html = html.replace("{{content}}","你好" + new Date().getTime());
             $(html).appendTo($("#result"));
         });
     })
</script>
</body>
</html>
