<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/26
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<!--header bar end-->
<div class="container">
    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-bell"></i>通知中心</span>
        </div>
        <button style="margin-left: 8px" disabled class="btn btn-mini" id="markBtn">标记为已读</button>
        <table class="table">
            <thead>
                <tr>
                    <th width="30"><input type="checkbox" id="ckFather"></th>
                    <th width="200">发布日期</th>
                    <th>内容</th>
                </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${not empty notifyList}">
                    <c:forEach items="${notifyList}" var="notify">
                        <c:choose>
                            <c:when test="${notify.state == 1}">
                                <tr style="text-decoration: line-through">
                                    <td width="30"></td>
                                    <td width="200">${notify.createtime}</td>
                                    <td>${notify.content}</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td width="30"><input type="checkbox" class="ckson" value="${notify.id}"></td>
                                    <td width="200">${notify.createtime}</td>
                                    <td>${notify.content}</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr><td colspan="3">暂时没有任何消息</td></tr>
                </c:otherwise>
            </c:choose>


            </tbody>
        </table>
    </div>
    <!--box end-->
</div>
<!--container end-->
</body>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script>
    $(function () {
        $("#ckFather").click(function () {
            //获取的是一个集合
            var $sons = $(".ckson");
            //对集合进行循环
            for(var i =0; i<$sons.length; i++){
                //$(this)[0]将jQuery对象转换为原生JavaScript对象
                $sons[i].checked = $(this)[0].checked;
            }
            if($(this)[0].checked == true){
                $("#markBtn").removeAttr("disabled");
            }else {
                $("#markBtn").attr("disabled","disabled");
            }

        });
        $(".ckson").click(function () {
            var $sons = $(".ckson");
            var num = 0;
            for(var i = 0; i<$sons.length;i++){
                if($sons[i].checked){
                    num++;
                }
            }
            if(num == $sons.length){
                //将ckFather转换为原生对象
                $("#ckFather")[0].checked = true;
            }else {
                $("#ckFather")[0].checked = false;
            }
            if(num >0){
                $("#markBtn").removeAttr("disabled");
            }else {
                $("#markBtn").attr("disabled","disabled");
            }
        });
        $("#markBtn").click(function () {
            var ids = [];
            var $sons = $(".ckson");
            for(var i =0; i<$sons.length; i++){
                if ($sons[i].checked == true){
                    ids.push($sons[i].value);
                }
            }
            alert($sons + ids.join(","));
            $.post("/notifyRead",{"ids":ids.join(",")},function (json) {
                if(json == "success"){
                    //加载history当前页
                    window.history.go(0);
                }
            });
        });
    });
</script>
</html>
