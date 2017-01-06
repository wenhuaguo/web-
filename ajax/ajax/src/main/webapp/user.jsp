<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/5
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <button id="btn">load xml</button>
    <table class="table">
        <thead>
            <td>ID</td>
            <td>姓名</td>
            <td>地址</td>
        </thead>
        <tbody id="tt">

        </tbody>
    </table>
    <script>
        (function () {
            document.querySelector("#btn").onclick = function () {
                //创建ajax引擎
                var xmlHttp = new XMLHttpRequest();
                //确定请求的方式和请求的地址
                xmlHttp.open("get","/user.xml");
               //配置回调函数
                xmlHttp.onreadystatechange = function () {
                    //判断服务器返回状态
                    if(xmlHttp.readyState == 4){
                        if(xmlHttp.status == 200){
                            //获取服务器端返回的XML数据
                            var xmlDoc = xmlHttp.responseXML;
                            var users = xmlDoc.getElementsByTagName("user");
                            var tt = document.querySelector("#tt");
                            for(var  i = 0; i < users.length; i++){
                                var id = users[i].getAttribute("id");
                                var name = users[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
                                var address = users[i].getElementsByTagName("address")[0].childNodes[0].nodeValue;
                                console.log(id + name + address);
                                //创建标签元素
                                var tr = document.createElement("tr");
                                var idTd = document.createElement("td");
                                var nameTd = document.createElement("td");
                                var addressTd = document.createElement("td");

                                //创建文本子标签
                                var idNode = document.createTextNode(id);
                                var nameNode = document.createTextNode(name);
                                var addNode = document.createTextNode(address);

                                idTd.appendChild(idNode);
                                nameTd.appendChild(nameNode);
                                addressTd.appendChild(addNode);
                                //将子标签放到tr中
                                tr.appendChild(idTd);
                                tr.appendChild(nameTd);
                                tr.appendChild(addressTd);
                                //将tr标签方法哦tbodyh中
                                tt.appendChild(tr);
                            }
                        }else {
                            alert("服务器响应错误" + xmlHttp.status);
                        }
                    };
                }
                xmlHttp.send();//发送请求
            };
        })();
    </script>
</body>
</html>
