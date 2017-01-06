<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/8
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示XML数据</title>
</head>
<body>
<input type="text" placeholder="RSS URL" id="url">
<button id="btn">load dataXml</button>
<ul id="list"></ul>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script>
    $(function () {

        $("#btn").click(function () {
            var rssUrl = $("#url").val();
            console.log(rssUrl);
            $("#list").html("");
            $.ajax({
                url : "/data.xml",
                type : "get",
                data : {"url":rssUrl},//参数类型可以是一个对象或者字符串如果是对象的话，对象内容格式必须为键值对
                success : function (xmlDoc) {
                    console.log(xmlDoc)
                    //find()方法查找指定元素标签，each对元素内容进行循环
                    $(xmlDoc).find("item").each(function(){
                        var link = $(this).find("link").text();
                       var title = $(this).find("title").text();//$(this)指的是循环当前元素
                        $("<li><a href='"+link+"' target='_blank'>"+title+"</a></li>").appendTo($("#list"));
                    });
                },
                error : function(){
                    alert("服务器异常")
                }
            });
        });
    });
</script>
</body>
</html>
