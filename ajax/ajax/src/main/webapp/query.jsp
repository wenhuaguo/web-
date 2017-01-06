<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/6
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="text" name="name" id="name">
<button id="btn">查询</button>
<p></p>
<script>
    (function () {
        document.querySelector("#btn").onclick = function () {
            var value = document.querySelector("#name").value;
            //1.创建ajax对象(引擎)
            var xmlHttp = new XMLHttpRequest();//获取ajax对象通过XMLHttpRequest
            //2.确定请求的方式和请求的地址
            xmlHttp.open("get","/query?name="+value);
            //3.配置回调函数
            xmlHttp.onreadystatechange = function () {
                //1.获取服务器端的状态码
                if(xmlHttp.readyState == 4){
                    //2.获取Http响应的状态码
                    if(xmlHttp.status == 200){
                        //3.获取服务器端的返回值
                        var xmlDoc = xmlHttp.responseXML;
                        var errorCode = xmlDoc.getElementsByTagName("errorCode")[0].childNodes[0].nodeValue;
                        if(errorCode == 0){
                            var ex = xmlDoc.getElementsByTagName("ex")[0].childNodes[0].nodeValue;
                            document.querySelector("p").innerText = ex;
                        }else {
                            alert("搜索异常" + errorCode);
                        }
                    }else {
                        alert("服务器返回异常" +xmlHttp.status)
                    }
                }
            }
            //4.发送请求
            xmlHttp.send();
        }
    })();
</script>
</body>
</html>
