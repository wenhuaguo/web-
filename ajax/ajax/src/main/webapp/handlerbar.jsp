<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/8
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>javascript模板</title>
</head>
<body>
    <button id="btn">javaScript 模板简化操作</button>
    <div id="result"></div>
    <script src="/static/js/jquery-1.11.3.min.js"></script>
    <script src="/static/js/handlebars-v4.0.5.js"></script>
    <script type="text/handlebars" id="temp1">
    <div id="entry">
     <%--{{!-- 模板注释 --}}--%>
    <h1>{{title}}</h1>
    <div class="body">
        {{{body}}}
    </div>
    <ul>
        {{#each data}}
        <li>{{name}}</li>
        {{/each}}
    </ul>
    {{#if vip}}
        <div>welcome!vip</div>
    {{/if}}
</div>
</script>
    <script>
        $(function () {
            $("#btn").click(function () {
                var html = $("#temp1").html();
                var template = Handlebars.compile(html);//强html元素进行编译
                var data = {
                    "title" : "模板的使用方式",
                    "body" : "<span style='color:red'>原来是这样的</span>",
                    "data" : [
                        {"name":"jack"},
                        {"name" : "rose"},
                        {"name" : "alex"},
                        {"name":"王五"}
                    ],
                    "vip" : true
                };
                var html = template(data);
                $(html).appendTo($("#result"));

            });
        });
    </script>
</body>
</html>
