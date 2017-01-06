<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/6
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <button id="btn">load json</button>
    <table class="table">
        <thead>
            <tr>
                <td>ID</td>
                <td>姓名</td>
                <td>地址</td>
            </tr>
        </thead>
        <tbody id="body">

        </tbody>
    </table>
    <span id="loading" style="display: none"><img src="/static/img/loding.gif"></span>
    <script src="/static/js/jquery-1.11.3.min.js"></script>
    <script src="/static/js/ajax.js"></script>
    <script>
        $(function () {
            var $tb = $("#body");
            $("#btn").click(function () {
                $.ajax({
                    url : "/data.json",
                    type : "get",
                    timeout : 1500000,
                    beforeSend : function () {
                        //请求发送之前做的函数
                      $("#loading").show();//将标签元素展示出来
                    },
                    success : function (data) {
                        //不用进行转换已经是json对象了
                       // var json = JSON.parse(data);
                        for(var i =0; i <data.length; i++ ){
                            var jsp = "<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td>"+data[i].address+"</td></tr>";
                            $(jsp).appendTo($tb);
                        }
                    },
                    error : function () {
                      alert("服务器异常");
                    },
                    complete : function () {
                        //无论是否success或error都会做的函数
                        $("#loading").hide();//将标签元素隐藏起来
                    }
                });
            });
        });
        
//        (function () {
//            document.querySelector("#btn").onclick = function () {
//                var bo = document.querySelector("#body");
//                //1.创建ajax引擎对象
//                var xmlHttp = new XMLHttpRequest();
//                //2.确定发送的方式以及地址
//                xmlHttp.open("get","/data.json");
//                //3.配置回调函数
//                xmlHttp.onreadystatechange = function () {
//                    //2.确定服务器状态码
//                    if(xmlHttp.readyState == 4){
//                        if(xmlHttp.status == 200){
//                            //获取服务器端返回内容
//                            var text = xmlHttp.responseText;
//                            //将字符串内容转为json对象
//                            var json = JSON.parse(text);
//                            for(var i = 0; i < json.length; i++){
//                                var user = json[i];
//                                var id = user.id;
//                                var name = user.name;
//                                var address = user.address;
//                                var idNode = document.createTextNode(id);
//                                var nameNode = document.createTextNode(name);
//                                var addressNode = document.createTextNode(address);
//
//                                var tr = document.createElement("tr");
//                                var idTd = document.createElement("td");
//                                var nameTd = document.createElement("td");
//                                var addressTd = document.createElement("td");
//
//                                idTd.appendChild(idNode);
//                                nameTd.appendChild(nameNode);
//                                addressTd.appendChild(addressNode);
//
//                                tr.appendChild(idTd);
//                                tr.appendChild(nameTd);
//                                tr.appendChild(addressTd);
//
//                                bo.appendChild(tr);
//
//                            }
//                        }else {
//                            alert("服务器返回异常" + xmlHttp.status);
//                        }
//                    }else {
//                        alert("服务器返回异常" + xmlHttp.readyState);
//                    }
//                };
//                //4.发送函数
//                xmlHttp.send();
//
//            }
//        })();

    </script>
</body>
</html>
